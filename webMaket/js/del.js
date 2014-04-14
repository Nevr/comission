function del(name) {
    if(confirm("Вы действительно хотите удалить \"" + name + "\"?")) {
        return true;
    }
     return false;
}