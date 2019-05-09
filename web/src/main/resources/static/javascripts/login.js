function login_check() {
    var uName=document.getElementById("userName");
    var uPwd=document.getElementById("userPwd");
    var setPwd="";
    setPwd=document.getElementById("pwd");
    var oError=document.getElementById("error_box");
    oError.innerHTML="<br>";

   
    if(uName.value.length<6||uName.value.length>11){
        oError.innerHTML='请输入6-11位的账号'; //账号输入格式验证
    }  
    else if(uPwd.value.length<6||uPwd.value.length>20){
        oError.innerHTML='请输入6-14位的密码';//密码位数输入验证
    }
    else{
        //如果通过密码校对则登录到系统
        window.location.href="find.html";
    } 
}
