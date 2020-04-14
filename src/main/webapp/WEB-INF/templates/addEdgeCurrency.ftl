<#ftl encoding="UTF-8"/>
<#import "base.ftl" as base/>
<#import "slideHeadMain.ftl" as slideHeadMain/>

<!DOCTYPE html>
<@base.head value="addEdgeCurrency"/>
<body>
<!--шапка-->
<@slideHeadMain.slideHead/>
<hr>
<!--<div id="pageAdd">-->
<form method="post" enctype="multipart/form-data" class="container-fluid row jumbotron">
    <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9 col-xl-10">
        <div class="pageAddHeader">
            <h1>Добавление ребра валюты</h1>
        </div>
        <div class="pageAddContent">
            <label class="pageAddLabel">
                <span class="pageAddTitle">Атрибуты:</span>
                <input type="text" name="nameCurrencyFrom" placeholder="из какой валюты" required>
                <input type="text" name="nameCurrencyTo" placeholder="в какой валюту" required>
                <input type="text" name="bankName" placeholder="какой банк предоставляет" required>
                <input type="text" name="urlFromData" placeholder="ссылка для парсинга" required>
                <input type="text" name="parsingXPath" placeholder="введите XPath" required>
                <td>
            </label>

        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 col-xl-2">
        <input class="btn5 btn-primary btn-lg" type="submit" name="saveNewCurrency" value="Сохранить"/>
    </div>
</form>

<!--</div>-->

<!--футер-->
<@base.end/>

</body>
</html>