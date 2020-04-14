<#ftl encoding="UTF-8"/>
<#macro head value>
    <head>

        <meta charset="utf-8">
        <meta name="view-port">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="ie-edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <link rel="stylesheet" href="../css/style.css">
        <script src="../js/AJAXFavourite.js"></script>
        <script src="../js/AJAXMessage.js"></script>
        <script src="../js/AJAXConnection.js"></script>

        <@extra/>
        <title>${value}</title>


    </head>
</#macro>


<#macro extra>
    <link rel="stylesheet" href="../css/forum.css">
    <link rel="stylesheet" href="../css/help.css">
</#macro>


<#macro slideHeadMain>
    <li class="navbar-item">
        <a href="/main" class="nav-link">Главная</a>
    </li>
    <li class="navbar-item active">
        <a href="/profile" class="nav-link">Профиль</a>
    </li>
    <li class="navbar-item">
        <a href="/favourite" class="nav-link">Избранное</a>
    </li>
    <li class="navbar-item">
        <a href="/forum" class="nav-link">Чат</a>
    </li>
    <li class="navbar-item">
        <a href="/help" class="nav-link">Помощь</a>
    </li>
</#macro>


<#macro content>


</#macro>


<#macro end>
    <footer class="container-fluid">
        <div class="container-fluid">
            <div class="row padding text-center">
                <div class="col-12">
                    <h2>Наши контакты</h2>
                </div>
                <div class="col-12 social padding">
                    <a href="#"><i class="fab fa-instagram"></i></a>
                    <a href="#"><i class="fab fa-facebook"></i></a>
                    <a href="#"><i class="fab fa-google-plus-g"></i></a>
                    <a href="#"><i class="fab fa-whatsapp"></i></a>
                    <a href="#"><i class="fab fa-vk"></i></a>
                </div>
            </div>
        </div>
    </footer>
</#macro>

<#--<#macro carousel>-->

<#--    <div class="carousel slide" data-ride="carousel" id="slides">-->
<#--        <ul class="carousel-indicators">-->
<#--            <li data-target="#slides" data-slide-to="0"></li>-->
<#--            <li data-target="#slides" data-slide-to="1"></li>-->

<#--        </ul>-->
<#--        <div class="carousel-inner">-->
<#--            <div class="carousel-item active">-->
<#--                <img src="../img/b1.jpg">-->
<#--                <div class="carousel-caption">-->
<#--                    <h1 class="display-4">Домашние тонкие блинчики</h1>-->
<#--                    <button type="button" class="btn btn-outline-light btn-lg">Добавить в избранное</button>-->
<#--                    <button type="button" class="btn btn-warning btn-lg">Перейти к рецепту</button>-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="carousel-item">-->
<#--                <img src="../img/b2.jpg">-->
<#--                <div class="carousel-caption">-->
<#--                    <h1 class="display-4">ИНГРЕДИЕНТЫ</h1>-->
<#--                    <h6>3 яйца</h6>-->
<#--                    <h6>1 ст. л. сахара</h6>-->
<#--                    <h6>1 ч. л. соли</h6>-->
<#--                    <h6>500 мл молока</h6>-->
<#--                    <h6>280 г муки</h6>-->
<#--                    <h6>3 ст. л. растительного масла плюс для жарки</h6>-->
<#--                    <h6>сливочное масло, сметана, варенье для подачи</h6>-->
<#--                </div>-->
<#--            </div>-->

<#--        </div>-->

<#--    </div>-->


<#--</#macro>-->
<#--<#macro carousel_with_input>-->
<#--    <div class="carousel slide" data-ride="carousel" id="slides">-->
<#--        <ul class="carousel-indicators">-->
<#--            <li data-target="#slides" data-slide-to="0"></li>-->
<#--            <li data-target="#slides" data-slide-to="1"></li>-->
<#--            <li data-target="#slides" data-slide-to="2"></li>-->
<#--        </ul>-->
<#--        <div class="carousel-inner">-->
<#--            <div class="carousel-item active">-->
<#--                <img src="../img/b1.jpg">-->
<#--                <div class="carousel-caption">-->
<#--                    <h1 class="display-4">Cook Yourself</h1>-->
<#--                    <a href="/login">-->
<#--                        <button type="button" class="btn btn-outline-light btn-lg">Войти</button>-->
<#--                    </a>-->
<#--                    <a href="/login">-->
<#--                        <button type="button" class="btn btn-warning btn-lg">Зарегстрироваться</button>-->
<#--                    </a>-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="carousel-item">-->
<#--                <img src="../img/b9.jpg">-->
<#--                <div class="carousel-caption">-->
<#--                    <h1 class="display-4">Cook Yourself</h1>-->
<#--                    <a href="/login">-->
<#--                        <button type="button" class="btn btn-outline-light btn-lg">Войти</button>-->
<#--                    </a>-->
<#--                    <a href="/login">-->
<#--                        <button type="button" class="btn btn-warning btn-lg">Зарегстрироваться</button>-->
<#--                    </a>-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="carousel-item">-->
<#--                <img src="../img/b8.jpg">-->
<#--                <div class="carousel-caption">-->
<#--                    <h1 class="display-4">Cook Yourself</h1>-->
<#--                    <a href="/login">-->
<#--                        <button type="button" class="btn btn-outline-light btn-lg">Войти</button>-->
<#--                    </a>-->
<#--                    <a href="/login">-->
<#--                        <button type="button" class="btn btn-warning btn-lg">Зарегстрироваться</button>-->
<#--                    </a>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--</#macro>-->

<#macro slideHeadWelcome>
    <li class="navbar-item">
        <a href="/register" class="nav-link">Регистрация</a>
    </li>
    <li class="navbar-item active">
        <a href="/login" class="nav-link">Войти</a>
    </li>
    <li class="navbar-item">
        <a href="/welcome" class="nav-link">Главная</a>
    </li>
</#macro>


<#--<#macro Recipes>-->
<#--    <script type="text/javascript">-->
<#--        var numSlide = 0;-->

<#--        <#if recipesAndItsIngredientsAndSteps??>-->
<#--        <#list recipesAndItsIngredientsAndSteps as recipeAndItsIngredientsAndSteps>-->
<#--        <#if recipeAndItsIngredientsAndSteps.recipe.name??>-->
<#--        var numDateSlide = 0;-->
<#--        document.write('<div class="carousel slide" data-ride="carousel" id="slides' + numSlide + '"> ');-->
<#--        document.write('<ul class="carousel-indicators"> ');-->
<#--        while (numDateSlide < ${recipeAndItsIngredientsAndSteps.stepsSize} +1) {-->
<#--            document.write('<li style="color: red; background: red;" data-target="#slides' + numSlide + '"data-slide-to="' + numDateSlide + '">');-->
<#--            document.write('</li>');-->
<#--            numDateSlide += 1;-->
<#--        }-->
<#--        document.write('</ul>');-->
<#--        numSlide += 1;-->
<#--        document.write('<div class="carousel-inner">');-->
<#--        document.write('<div class="carousel-item active">');-->
<#--        document.write('<img src="${recipeAndItsIngredientsAndSteps.recipe.photo}"/>');-->
<#--        document.write('<div class="carousel-caption">');-->
<#--        document.write('<h1 class="display-4">${recipeAndItsIngredientsAndSteps.recipe.name}</h1>');-->
<#--        <#if recipeAndItsIngredientsAndSteps.ingredients??>-->
<#--        document.write('<h4 class="display-8">ИНГРЕДИЕНТЫ:</h4>');-->
<#--        <#list recipeAndItsIngredientsAndSteps.ingredients as ingredient>-->
<#--        document.write('<h6>${ingredient.name} ${ingredient.amount}</h6>');-->
<#--        </#list>-->
<#--        </#if>-->
<#--        document.write('<a href="/recipe/${recipeAndItsIngredientsAndSteps.recipe.id}">');-->
<#--        document.write('<button input type="button"  class="btn btn-warning btn-lg">Перейти к рецепту</button>');-->
<#--        document.write('</a>')-->
<#--        document.write('<input type="button" onClick="voteFavourite(\'${userId}\',\'${recipeAndItsIngredientsAndSteps.recipe.id}\')" class="btn btn-success btn-lg" value="Добавить в избранное"/>');-->
<#--        document.write('<div  id="vote_favourite_status"></div>');-->
<#--        document.write('</div>');-->
<#--        document.write('</div>');-->
<#--        <#if recipeAndItsIngredientsAndSteps.steps??>-->
<#--        <#list recipeAndItsIngredientsAndSteps.steps as step>-->
<#--        document.write('<div class="carousel-item">');-->
<#--        document.write('<img src="${step.imageUrl!}"/>');-->
<#--        document.write('<div class="carousel-caption">');-->
<#--        document.write('<h2>${step.text}</h2>');-->
<#--        document.write('</div>');-->
<#--        document.write('</div>');-->
<#--        </#list>-->
<#--        </#if>-->
<#--        document.write('</div>');-->
<#--        document.write('</div>');-->
<#--        document.write('<hr>');-->
<#--        </#if>-->
<#--        </#list>-->

<#--        </#if>-->
<#--    </script>-->


<#--</#macro>-->

