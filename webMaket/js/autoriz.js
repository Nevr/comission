function check(login, pass) {
	if (login == "p.leshchov@mail.ru" && pass == "admin")
		document.getElementById('enter').action = "./result/results.html";
	else if (login == "user@mail.ru" && pass == "user")
		document.getElementById('enter').action = "./abiturient/abiturientFix.html";
}