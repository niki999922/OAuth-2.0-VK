<#import "/spring.ftl" as spring>
<#import "Common.ftlh" as c>

<@c.page>
    <main>
        <div class="enter form-box">
            <div class="header">Enter</div>
            <div class="body">
                <a href="https://oauth.vk.com/authorize?client_id=7018875&display=page&redirect_uri=https://some-auth.herokuapp.com&scope=offline,friends&response_type=code&v=5.52"
                   title="Зайти через ВКонтакте"><img src="<@spring.url '/img/vk.png'/>" alt="VK" title="VK"/></a>
            </div>
        </div>
    </main>
</@c.page>
