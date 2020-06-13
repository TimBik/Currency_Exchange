<#ftl encoding="UTF-8"/>
<#import "base.ftl" as base/>
<#import "slideHeadAdmin.ftl" as slideHead/>
<#import "printStatus.ftl" as printStatus/>

<!DOCTYPE html>
<@base.head value="addCurrency"/>
<body>
<!--шапка-->
<@slideHead.slideHead/>
<hr>
<!--<div id="pageAdd">-->
<form method="post" enctype="multipart/form-data" class="container-fluid row jumbotron" modelAttribute="currencyForm">
    <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9 col-xl-10">
        <div class="pageAddHeader">
            <h1>Добавление валюты</h1>
        </div>
        <div class="pageAddContent">
            <label class="pageAddLabel">
                <span class="pageAddTitle">Атрибуты:</span>
                <input type="text" name="name" placeholder="название валюты" required>
                <input type="number" step="any" name="approximateCost"
                       placeholder="средняя ценя за 1 относительно рубля" required>
<#--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
                <td>
            </label>

        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 col-xl-2">
        <input class="btn5 btn-primary btn-lg" type="submit" name="saveNewCurrency" value="Сохранить"/>
    </div>
</form>

<@printStatus.print/>

<!--</div>-->

<!--футер-->
<@base.end/>

</body>
</html>