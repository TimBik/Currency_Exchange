<#--<#include "base.ftl"/>-->
<#macro slideHead>
    <nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
        <div class="container-fluid">
            <a href="#" class="navbar-brad"><img src="../img/LOGO.png"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="navbar-item">
                        <a href="addBank" class="nav-link">Добавить банк</a>
                    </li>
                    <li class="navbar-item active">
                        <a href="addCurrency" class="nav-link">Добавить валюту</a>
                    </li>
                    <li class="navbar-item">
                        <a href="addEdgeCurrency" class="nav-link">Добавить валютное ребро</a>
                    </li>
                    <li class="navbar-item">
                        <a href="addAdmin" class="nav-link">Добавить админа</a>
                    </li>
                </ul>
            </div>

        </div>
    </nav>
</#macro>