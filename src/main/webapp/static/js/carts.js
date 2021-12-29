function getContextPath() {
    let pathName = document.location.pathname;
    let index = pathName.substr(1).indexOf("/");
    let result = pathName.substr(0, index + 1);
    return result;
}

let path = getContextPath();

function index_btn() {
    window.location.href = path + "/";
}

function order_btn() {
    window.location.href = path + "/order";
}
//进入页面
$(function (){
    getShoppingCarCom();
})
//获取购物车内容
function getShoppingCarCom(){
    $.ajax({
        url:path+"/shoppingCar/coms",
        type:"get",
        success:function (result){
            build_shopping_car(result)
        }
    })
}
function build_shopping_car(result){
    $('#comMain').empty();
    let carComs = result.extend.shoppingCar.carComs;
    $.each(carComs,function (index,item){
        let imgPath = path+'/static/image/'+item.commodity.imgs[0].iName;
        let allP = parseInt(item.commodity.cPrice)*parseInt(item.cCount);
        let fuXuan=$('<div class="col-lg-1 col-md-1" style="padding-left: 5px;"></div>').append($('<li class="list_chk"></li>').append($('<input type="checkbox" class="check_item"/>')).append($('<label></label>')));
        let image =$('<div class="col-lg-2 col-md-2"></div>').append($('<li class="list_con"></li>').append($('<div class="list_img"></div>').append($('<a href="javascript:;">').append($('<img >').attr('src',imgPath)))));
        let mess =$('<div class="col-lg-3 col-md-3" style="padding-left: 0;"></div>').append($('<li class="list_con"></li>').append($('<div class="list_text" style="padding-left: 15px;"></div>').append($('<a href="javascript:;">').text(item.commodity.cName))));
        let price =$('<div class="col-lg-1 col-md-1"></div>').append($('<li class="list_price"></li>').append($('<p class="price" style="font-size: larger;color: red; font-family: microsoft yahei;"></p>').text('￥'+item.commodity.cPrice)));
        let num =$('<div class="col-lg-2 col-md-2"></div>').append($('<li class="list_amount"></li>').append($('<div class="amount_box"></div>').append($('<a href="javascript:;" class="reduce reSty" style="text-decoration: none;">-</a>')).append($('<input type="text" class="sum" style="height: 23px;width: 41px;">').attr('value',item.cCount)).append($('<a href="javascript:;" class="plus" style="text-decoration: none;">+</a>'))));
        let allPrice =$('<div class="col-lg-1 col-md-1"></div>').append($('<li class="list_sum"></li>').append($('<p class="sum_price" style="font-size: larger; font-family: microsoft yahei;"></p>').text('￥'+allP)));
        let delBtn=$('<div class="col-lg-2 col-md-2"></div>').append($('<li class="list_op">').append($('<p class="del"></p>').append($('<button type="button" class="btn btn-danger delBtn" style="font-family: helvetica;"></button>').text('删除').attr('comId',item.commodity.cId))));
        let con =$('<div class="row" style="padding-bottom: 15px;"></div>').append(fuXuan).append(image).append(mess).append(price).append(num).append(allPrice).append(delBtn);
        $('<div class="cartBox"></div>').append($('<div class="order_content"></div>').append($('<ul class="order_lists"></ul>').append(con))).appendTo('#comMain');

    })
}
//移出购物车
$(document).on("click",".delBtn",function () {
    if (confirm("确定要将此商品移出购物车吗？")){
        $.ajax({
            url:path+"/shoppingCar/"+$(this).attr('comId'),
            type: 'delete',
            success:function (result){
                alert('移出成功！');
                getShoppingCarCom();
            }
        })
    }
})

//全选全不选
$("#check_all").click(function () {
    $(".check_item").prop("checked", $(this).prop("checked"));
    totalMoney();
});
$(document).on("click",".check_item",function () {
    let flag = $(".check_item:checked").length == $(".check_item").length;
    $("#check_all").prop("checked", flag)
    totalMoney();
})

//=================================================商品数量==============================================
$(document).on("click",".plus",function () {
    let $inputVal = $(this).prev('input'),
        $count = parseInt($inputVal.val()) + 1,
        $obj = $(this).parents('.amount_box').find('.reduce'),
        $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
        $price = $(this).parents('.order_lists').find('.price').html(),  //单价
        $priceTotal = $count * parseInt($price.substring(1));
    $inputVal.val($count);
    $priceTotalObj.html('￥' + $priceTotal);
    if ($inputVal.val() > 1 && $obj.hasClass('reSty')) {
        $obj.removeClass('reSty');
    }
    totalMoney();
});

$(document).on("click",".reduce",function () {
    let $inputVal = $(this).next('input'),
        $count = parseInt($inputVal.val()) - 1,
        $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
        $price = $(this).parents('.order_lists').find('.price').html(),  //单价
        $priceTotal = $count * parseInt($price.substring(1));
    if ($inputVal.val() > 1) {
        $inputVal.val($count);
        $priceTotalObj.html('￥' + $priceTotal);
    }
    if ($inputVal.val() == 1 && !$(this).hasClass('reSty')) {
        $(this).addClass('reSty');
    }
    totalMoney();
});

$(document).on("keyup",".sum",function () {
    let $count = 0,
        $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
        $price = $(this).parents('.order_lists').find('.price').html(),  //单价
        $priceTotal = 0;
    if ($(this).val() == '') {
        $(this).val('1');
    }
    $(this).val($(this).val().replace(/\D|^0/g, ''));
    $count = $(this).val();
    $priceTotal = $count * parseInt($price.substring(1));
    $(this).attr('value', $count);
    $priceTotalObj.html('￥' + $priceTotal);
    totalMoney();
})

//======================================总计==========================================

function totalMoney() {
    let total_money = 0;
    let total_count = 0;
    let calBtn = $('.calBtn a');
    $(".check_item").each(function () {
        if ($(this).is(':checked')) {
            let goods = parseInt($(this).parents('.order_lists').find('.sum_price').html().substring(1));
            let num = parseInt($(this).parents('.order_lists').find('.sum').val());
            total_money += goods;
            total_count += num;
        }
    });
    $('.total_text').html('￥' + total_money);
    $('.piece_num').html(total_count);

    // console.log(total_money,total_count);

    if (total_money != 0 && total_count != 0) {
        if (!calBtn.hasClass('btn_sty')) {
            calBtn.addClass('btn_sty');
        }
    } else {
        if (calBtn.hasClass('btn_sty')) {
            calBtn.removeClass('btn_sty');
        }
    }
}
//提交订单
$('#calBtn').click(function(){
    let carComs=[];
    $.each($(".check_item:checked"),function () {
        let comId = $(this).parents("ul").find("li:eq(6)").find("button:eq(0)").attr("comId");
        let comCount =$(this).parents("ul").find("li:eq(4)").find("input:eq(0)").val();
        let carCom ={"cId":comId,"cCount":comCount};
        carComs.push(carCom);
    })
    $.ajax({
        url:path+'/order/submit',
        type:'post',
        contentType:'application/json',
        data:JSON.stringify(carComs),
        success:function (result){
            if (result.code==100){
                alert("订单已提交！");
                getShoppingCarCom();
            }else {
                alert("请先填写一个收货地址！");
                window.location.href=path+"/userCenter";
            }
        }
    })
})


