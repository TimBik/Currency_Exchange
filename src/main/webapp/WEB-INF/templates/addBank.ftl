<#ftl encoding="UTF-8"/>
<#import "base.ftl" as base/>
<#import "slideHeadAdmin.ftl" as slideHead/>
<#import "printStatus.ftl" as printStatus/>
<!DOCTYPE html>
<@base.head value="addBank"/>
<body>
<!--шапка-->
<@slideHead.slideHead/>
<hr>
<!--<div id="pageAdd">-->
<form method="post" enctype="multipart/form-data" class="container-fluid row jumbotron">
    <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9 col-xl-10">
        <div class="pageAddHeader">
            <h1>Добавление банка</h1>
        </div>
        <div class="pageAddContent">
            <label class="pageAddLabel">
                <span class="pageAddTitle">Название:</span>
                <input type="text" name="nameBank" required>
                <td><input type="file" name="photoBank">
                <td>
            </label>

        </div>
<#--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
    </div>
    <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 col-xl-2">
        <input class="btn5 btn-primary btn-lg" type="submit" name="saveNewRecipe" value="Сохранить"/>
    </div>
</form>

<@printStatus.print/>
<!--</div>-->

<!--футер-->
<@base.end/>

</body>
</html>