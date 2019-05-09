function changePwd_check() {
    var uName=document.getElementById("userName");
    var npwd=document.getElementById("newPwd");
    var npwd2="";
    npwd2=document.getElementById("newPwd2");
    var tel=document.getElementById("userTel");
    var oError=document.getElementById("error_box");
    oError.innerHTML="<br>";
   
    if(uName.value.length<1){
        oError.innerHTML='请输入您的账号'; //账号输入格式验证
    }  
    else if(tel.value.length!=11){
        oError.innerHTML='请输入11位的手机号';//手机号位数输入验证
    }
    else if(npwd.value.length<6||npwd.value.length>20){
        oError.innerHTML='请输入6-14位的新密码';//密码位数输入验证
    }
    else if(npwd2.value==""){
        oError.innerHTML='请再次输入密码';//重复密码输入验证
    }
    else if(npwd.value!=npwd2.value){
        oError.innerHTML='输入密码不一致';//重复密码输入验证
    }
    else{
        //信息填写正确后将更新密码，并跳转到登录界面  
        alert("密码修改成功！")
        window.location.href="login.html";
    }

}
