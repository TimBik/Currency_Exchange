<#ftl encoding="UTF-8"/>
<#import "base.ftl" as base/>

<#import "slideHeadMain.ftl" as slideHeadMain/>
<#import "headWelcome.ftl" as headWelcome/>

<!DOCTYPE html>

<html lang="en">
<@headWelcome.head value="Main"/>
<body>
<@slideHeadMain.slideHead/>

<#if banks??>
    Банки за которыми мы следим, готовые обменивать валюты
    <script>var i = 1</script>
    <table class="table-menu">
        <tbody>
        <tr>
            <th>№</th>
            <th>Наименование банка</th>
            <th>рейтинг наших пользователей</th>
        </tr>
        <#list banks as bank>
            <tr>
                <td>
                    <script>
                        document.write(i);
                        i++;
                    </script>
                </td>
                <td>
                    <a href="http://localhost:8080/banks/${bank.name} ">${bank.name}</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

<@base.end/>
</body>
</html>