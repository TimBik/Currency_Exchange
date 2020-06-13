<#ftl encoding="UTF-8"/>
<#import "base.ftl" as base/>
<#import "slideHeadMain.ftl" as slideHeadMain/>
<#import "headWelcome.ftl" as headWelcome/>
<!DOCTYPE html>
<html lang="en">
<@headWelcome.head value="Finder"/>
<body>
<!--шапка-->
<@slideHeadMain.slideHead/>

<!--поиск-->
<form action="/finder" method="post" class="d1">
    <input type="text" name="currencyFrom" placeholder="из валюты">
    <input type="text" name="currencyTo" placeholder="в валюту">
<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->

    <button type="submit"></button>
</form>

<#if edgeCurrencyList??>
    Выгоднее всего размен будет если будете обменивать валюту в этих банках, по данному курсу:
    <script>var i = 1</script>
    <table class="table-menu">
        <tbody>
        <tr>
            <th>№</th>
            <th>Наименование банка</th>
            <th>из валюты</th>
            <th>в валюту</th>
            <th>цена за единицу</th>
        </tr>
        <#list edgeCurrencyList as edgeCurrency>
            <tr>
                <td>
                    <script>
                        document.write(i);
                        i++;
                    </script>
                </td>
                <td>${edgeCurrency.bankName}</td>
                <td>${edgeCurrency.nameCurrencyFrom}</td>
                <td>${edgeCurrency.nameCurrencyTo}</td>
                <td>${edgeCurrency.costByOne}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>
<#if status??>
    ${status}
</#if>
<@base.end/>


</body>
</html>