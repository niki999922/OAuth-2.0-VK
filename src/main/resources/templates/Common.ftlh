<#-- @ftlvariable name="user" type="ru.ath.auth.domain.User" -->
<#import "/spring.ftl" as spring/>
<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>VKlog</title>
        <link rel="shortcut icon" type="image/png" href="<@spring.url '/img/vk.png'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/normalize.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/form.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/datatable.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/article.css'/>">
    </head>
    <body>
    <header>
        <a href="/"><img src="<@spring.url '/img/logo.png'/>"/></a>
        <div class="languages">
            <a href="#"><img src="<@spring.url '/img/gb.png'/>" alt="In English" title="In English"/></a>
            <a href="#"><img src="<@spring.url '/img/ru.png'/>" alt="In Russian" title="In Russian"/></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
            <a href="https://vk.com/id${user.id}">${user.first_name}</a> |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/login">Login</a>
            </#if>
        </div>
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
            </ul>
        </nav>
    </header>
    <div class="middle">
        <#nested/>
    </div>
    <footer>
        Made by Nikita Kochetkov
    </footer>
    </body>
    </html>
</#macro>
