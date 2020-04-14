// javascript-код голосования из примера
function voteFavourite(userId, recipeId) {
    // (1) создать объект для запроса к серверу
    var req = getXmlHttp()

    // (2)
    // span рядом с кнопкой
    // в нем будем отображать ход выполнения
    var statusElem = document.getElementById('vote_favourite_status')


    // (3) задать адрес подключения
    req.open('Post', '/forFavourites?UserId=' + userId + '&RecipeId=' + recipeId, true);

    // объект запроса подготовлен: указан адрес и создана функция onreadystatechange
    // для обработки ответа сервера

    // (4)
    req.send(null);  // отослать запрос

    // (5)
    statusElem.innerHTML = 'Ожидаю ответа сервера...'

    req.onreadystatechange = function () {
        // onreadystatechange активируется при получении ответа сервера

        if (req.readyState == 4) {
            // если запрос закончил выполняться

            statusElem.innerHTML = req.statusText // показать статус (Not Found, ОК..)

            if (req.status == 200) {
                // если статус 200 (ОК) - выдать ответ пользователю
                alert("Рецепт добавлен в избранное");
            } else {
                alert("Извините, произошла ошибка");
            }
        }

    }
}
