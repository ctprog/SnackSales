function getContextPath(){
    let pathName = document.location.pathname;
    let index = pathName.substr(1).indexOf("/");
    let result = pathName.substr(0,index+1);
    return result;
}
let path = getContextPath();
//登录前端信息校验
function validate_login_from(){
    let uId = $("#login-uId").val();
    let reguId = /^[1-9][0-9]{5}$/;
    if (!reguId.test(uId)){
        show_validate_msg("#login-uId","error","ID格式不正确！");
        return false;
    } else {
        show_validate_msg("#login-uId","success","");
    }

    let uPassword = $("#login-uPassword").val();
    let reguPassword = /^[a-zA-Z0-9]{6,12}$/
    if (!reguPassword.test(uPassword)){
        show_validate_msg("#login-uPassword","error","密码格式不正确！");
        return false;
    } else {
        show_validate_msg("#login-uPassword","success","");
    }
    return true;
}
//注册前端信息校验
function validate_register_from(){
    let uId = $("#reg-uId").val();
    let reguId = /^[1-9][0-9]{5}$/;
    if (!reguId.test(uId)){
        show_validate_msg("#reg-uId","error","ID格式不正确！");
        return false;
    } else {
        show_validate_msg("#reg-uId","success","");
    }

    let uName = $("#reg-uName").val();
    let reguName = /(^[a-zA-Z]{1}[a-zA-Z\s]{0,20}[a-zA-Z]{1}$)|(^(?:[\u4e00-\u9fa5·]{2,16})$)/
    if (!reguName.test(uName)){
        show_validate_msg("#reg-uName","error","用户名格式不正确！");
        return false;
    } else {
        show_validate_msg("#reg-uName","success","");
    }

    let uPhone = $("#reg-uPhone").val();
    let reguPhone = /^(?:(?:\+|00)86)?1\d{10}$/
    if (!reguPhone.test(uPhone)){
        show_validate_msg("#reg-uPhone","error","电话号码格式不正确！");
        return false;
    } else {
        show_validate_msg("#reg-uPhone","success","");
    }

    let uPassword = $("#reg-uPassword").val();
    let reguPassword = /^[a-zA-Z0-9]{6,12}$/
    if (!reguPassword.test(uPassword)){
        show_validate_msg("#reg-uPassword","error","密码格式不正确！");
        return false;
    } else {
        show_validate_msg("#reg-uPassword","success","");
    }

    let uPassword2 = $("#reg-uPassword2").val();
    if (uPassword!=uPassword2){
        show_validate_msg("#reg-uPassword2","error","确认密码与密码不一致！");
        return false;
    } else {
        show_validate_msg("#reg-uPassword2","success","");
    }
    return true;
}
//校验信息显示
function show_validate_msg(ele,status,msg){
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");
    if ("success"==status){
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    } else if ("error"==status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}
//登录
function user_login(){
    if(validate_login_from()){
        $.ajax({
            url:path+"/user/login",
            type:"POST",
            data:$("#login-form").serialize(),
            async:false,
            success:function (result) {
                if (result.code == 200){
                    show_validate_msg("#login-uPassword","error",result.extend.error)
                } else if (result.code == 100){
                    window.location.href = path+"/";
                }
            }
        })
    }
}
//注册
function user_register(){
    if (validate_register_from()){
        let formData = new FormData($("#register-form")[0]);
        $.ajax({
            url: path+"/user/register",
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            success:function (result){
                if(result.code==100){
                    alert("注册成功！请登录！");
                    window.location.href = path+"/register";
                }else {
                    if (result.extend.merror!=null){
                        show_validate_msg("#reg-uId","error",result.extend.merror);
                    }else {
                        show_validate_msg("#head_image","error",result.extend.error);
                    }
                }
            }
        })
    }
}