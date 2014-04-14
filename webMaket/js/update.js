function update(name) {
    if(confirm("Вы действительно хотите сохранить изменения \"" + name + "\"?")) {
        return true;
    }
     return false;
}