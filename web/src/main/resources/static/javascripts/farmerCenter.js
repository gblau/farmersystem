function myShop() {
        var Button1 = document.getElementById("Button1");
        var Button2 = document.getElementById("Button2");
        var Button3 = document.getElementById("Button3");
        Button1.style.display = "block";
        Button2.style.display = "none";
        Button3.style.display = "none";
    }

function myFarm() {
    var Button1 = document.getElementById("Button1");
    var Button2 = document.getElementById("Button2");
    var Button3 = document.getElementById("Button3");
    Button1.style.display = "none";
    Button2.style.display = "block";
    Button3.style.display = "none";
}

function myInfo() {
    var Button1 = document.getElementById("Button1");
    var Button2 = document.getElementById("Button2");
    var Button3 = document.getElementById("Button3");
    Button1.style.display = "none";
    Button2.style.display = "none";
    Button3.style.display = "block";
}

/*我的农庄*/
function add() {
        var trObj = document.createElement("tr");
        trObj.id = new Date().getTime();

        trObj.innerHTML = "<style>.stock{height:35px;width:115px;border-radius:3px;border:1.2px solid #484948;color:#050505;text-indent:3px;}"
        +".address{height:35px;border-radius:3px;border:1.2px solid #484948;color:#050505;text-indent:3px;width:540px;}"
        +".name{height:35px;width:115px;border-radius:3px;border:1.2px solid #484948;color:#050505;text-indent:3px;}"
        +".des{height:35px;width:295px;border-radius:3px;border:1.2px solid #484948;color:#050505;text-indent:3px;}"
        +"</style>"
        +"<td><input class='name' type='text' name='name'/></td>"
        +"<td><input class='maxPerson' type='text' name='stock'/></td>"
        +"<td><input class='address' name='address'/></td>"
        +"<td><input class='introduction' type='text' name='description'/></td>"
        +"<td><input type='submit' value='提 交'/><input type='button' value=' 删 除 ' onclick='del(this)'></td>";

        document.getElementById("tb").appendChild(trObj);
    }

function del(obj) {
    var trId = obj.parentNode.parentNode.id;
    var trObj = document.getElementById(trId);
    document.getElementById("tb").removeChild(trObj);
} 


/*我的店铺*/
function add01() {
    var trObj = document.createElement("tr");
    trObj.id = new Date().getTime();

    trObj.innerHTML = "<style>.in{height:35px;width:115px;border-radius:3px;border:1.2px solid #484948;color:#050505;text-indent:3px;}" +
        ".description{height:35px;border-radius:3px;border:1.2px solid #484948;color:#050505;text-indent:3px;width:598px;}" +
        "</style>" +
        "<td><input class='in' type='text' name='name'/></td>" +
        "<td><input class='in' type='text' name='price'/></td>" +
        "<td><input class='in' type='text' name='stock'/></td>" +
        "<td><input class='description' name='description'/></td>" +
        "<td><input class='in' type='image' name='image'/></td>" +
        "<td><input type='button' value=' 删 除 ' onclick='del01(this)'></td>";

    document.getElementById("tb01").appendChild(trObj);
}

function del01(obj) {
    var trId = obj.parentNode.parentNode.id;
    var trObj = document.getElementById(trId);
    document.getElementById("tb01").removeChild(trObj);
}

