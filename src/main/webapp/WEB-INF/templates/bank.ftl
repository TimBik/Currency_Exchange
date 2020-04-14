<#ftl encoding="UTF-8"/>
<#import "base.ftl" as base/>
<#import "slideHeadMain.ftl" as slideHeadMain/>
<#import "headWelcome.ftl" as headWelcome/>
<!DOCTYPE html>
<html lang="en">
<#if bank??>
    <@headWelcome.head value="${bank.name}"/>
</#if>
<#if status??><@headWelcome.head value="${status}"/></#if>
<body>
<!--шапка-->
<@slideHeadMain.slideHead/>

<#if bank??>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-0 col-sm-1 col-md-2"></div>
            <div class="col-md-8">
                <span><h3>${bank.name}</h3></span>

                <div class="row">
                    <div class="col-xs-4 col-sm-12 col-md-10 col-lg-4">
                        <div class="profile"><img src="../img/cook.jpg" style=""></div>
                        <hr>
                        <!--rating-->
                        <#--                    <ul class="rating">-->
                        <#--                        <li class="current" style="width: 0%;"><span class="star1" title="Плохо"></span></li>-->
                        <#--                        <li><span class="star2" title="Нормально"></span></li>-->
                        <#--                        <li><span class="star3" title="Средне"></span></li>-->
                        <#--                        <li><span class="star4" title="Хорошо"></span></li>-->
                        <#--                        <li><span class="star5" title="Отлично"></span></li>-->
                        <#--                    </ul>-->
                        <#--                    <hr>-->

                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8">
                        Предоставляемые банком валюты:
                        <script>var i = 1</script>
                        <table class="table-menu">
                            <tbody>
                            <tr>
                                <th>№</th>
                                <th>Наименование валюты</th>
                                <th>цена за единицу в рублях</th>
                            </tr>
                            <#--придется менять на ребра, так как хранятся approximateCost-->
                            <#if bank.currencies??>
                                <#list bank.currencies as currency>
                                    <tr>
                                        <td>
                                            <script>
                                                document.write(i);
                                                i++;
                                            </script>
                                        </td>
                                        <td>${currency.name}</td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if status??>
    ${status}
</#if>
<@base.end/>


</body>
</html>