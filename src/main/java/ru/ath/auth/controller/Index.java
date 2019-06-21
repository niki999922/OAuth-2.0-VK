package ru.ath.auth.controller;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.friends.responses.SearchResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ath.auth.domain.User;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@SuppressWarnings("Duplicates")
@Controller
public class Index {
    private static Integer APP_ID = "put here your own app_id";
    private static String CLIENT_SECRET = "put here your own clien_secter of app";
    private static String REDIRECT_URI = "https://some-auth.herokuapp.com";

    @ModelAttribute(name = "friends")
    public List<User> getFriends(HttpSession httpSession) throws IOException, ClientException, ApiException {
        if (httpSession.getAttribute("access_token") == null) {
            return null;
        }
        UserActor actor = new UserActor((Integer) httpSession.getAttribute("user_id"), (String) httpSession.getAttribute("access_token"));
        ArrayList<User> list = new ArrayList<>();
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        GetResponse getResponse = vk.friends().get(actor).execute();
        SearchResponse searchResponse = vk.friends()
                .search(actor, actor.getId())
                .count(getResponse.getCount())
                .fields(Fields.PHOTO_100)
                .execute();
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            int rand;
            do {
                rand = random.nextInt(getResponse.getCount());
            } while (set.contains(rand));
            set.add(rand);
            UserFull item = searchResponse.getItems().get(rand);
            list.add(new User(item.getId().toString(), item.getFirstName(), item.getLastName(), vk.users().get(actor).userIds(item.getId().toString()).fields(Fields.PHOTO_100).execute().get(0).getPhoto100().toString(), item.getStatus()));
        }return list;

    }

    @ModelAttribute(name = "user")
    public User getUser(HttpSession httpSession) throws IOException, ClientException, ApiException {
        if (httpSession.getAttribute("access_token") == null) {
            return null;
        }
        UserActor actor = new UserActor((Integer) httpSession.getAttribute("user_id"), (String) httpSession.getAttribute("access_token"));
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        List<UserXtrCounters> list = vk.users()
                .get(actor)
                .fields(Fields.PHOTO_400_ORIG)
                .execute();
        UserXtrCounters userXtrCounters = list.get(0);
        return new User(userXtrCounters.getId().toString(), userXtrCounters.getFirstName(), userXtrCounters.getLastName(), userXtrCounters.getPhoto400Orig().toString(), userXtrCounters.getStatus());

    }

    @GetMapping(path = "")
    public String index(@RequestParam(name = "code", required = false, defaultValue = "none") String code,
                        HttpSession httpSession,
                        Model model) throws IOException, ClientException, ApiException {
        if (!code.equals("none") && httpSession.getAttribute("access_token") == null) {
            TransportClient transportClient = new HttpTransportClient();
            VkApiClient vk = new VkApiClient(transportClient);
            UserAuthResponse authResponse = vk.oAuth()
                    .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                    .execute();
            UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
            httpSession.setAttribute("access_token", actor.getAccessToken());
            httpSession.setAttribute("user_id", actor.getId());
            model.addAttribute("user", getUser(httpSession));
            model.addAttribute("friends", getFriends(httpSession));
        }
        return "Index";
    }
}
