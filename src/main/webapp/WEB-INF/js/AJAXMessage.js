// javascript-код голосования из примера
function voteMessage(userId, message, login) {
    // (1) создать объект для запроса к серверу
    var req = getXmlHttp()

    // (2)
    // span рядом с кнопкой
    // в нем будем отображать ход выполнения
    var statusElem = document.getElementById('vote_message_status')


    // (3) задать адрес подключения
    req.open('Post', '/forum?userId=' + userId + '&message=' + message + '&login=' + login, true);

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
                alert("Все получилось)))");
                window.location.replace(window.location.href);
            } else {
                alert("Извините, произошла ошибка");
            }
        }

    }
}

