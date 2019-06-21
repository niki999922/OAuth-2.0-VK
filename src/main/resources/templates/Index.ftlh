<#-- @ftlvariable name="user" type="ru.ath.auth.domain.User" -->
<#-- @ftlvariable name="friends" type="ru.ath.auth.domain.User[]" -->
<#import "Common.ftlh" as c>

<@c.page>
    <aside>
        <#if friends??>
        <#list friends as friend>
            <section>
                <div class="header">
                 <a href="https://vk.com/id${friend.id}">${friend.first_name} ${friend.last_name}</a>
                </div>
                <div class="body">
                    <a href="https://vk.com/id${friend.id}"><img src="${friend.photo_100}"/></a>
                </div>
            </section>
        </#list>
        </#if>
    </aside>
    <main>
        <#if user??>
            <div class="caption">${user.first_name} ${user.last_name}</div>
            <div class="fieldinfo">
                <a href="https://vk.com/id${user.id}"><img src="${user.photo_100}"/></a>
            </div>
        </#if>
    </main>
</@c.page>