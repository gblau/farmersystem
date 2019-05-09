function register_check() {
    var uName=document.getElementById("userName");
    var uPwd=document.getElementById("userPwd");
    var setPwd="";
    setPwd=document.getElementById("pwd");
    var tel=document.getElementById("userTel");
    var oError=document.getElementById("error_box");
    oError.innerHTML="<br>";
   
    if(uName.value.length<6||uName.value.length>11){
        oError.innerHTML='请输入6-11位的账号'; //账号输入格式验证
    }  
    else if(uPwd.value.length<6||uPwd.value.length>20){
        oError.innerHTML='请输入6-14位的密码';//密码位数输入验证
    }
    else if(setPwd.value==""){
        oError.innerHTML='请再次输入密码';//重复密码输入验证
    }
    else if(setPwd.value!=userPwd.value){
        oError.innerHTML='输入密码不一致';//重复密码输入验证
    }
    else if(tel.value.length!=11){
        oError.innerHTML='请输入11位的手机号';//手机号位数输入验证
    }
    else{
        //信息填写正确后将数据写入数据库并提示注册成功，并跳转到登录界面  
        alert("注册成功！");
        window.location.href="login.html";    
    }
}
