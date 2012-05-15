
function  addToCart(quantity,idProduct){
    var result;
    $.ajax({
        type: "GET",
        url: "/addShoppingCart.vn",
        data: "id="+idProduct+"&number="+quantity,
        dataType: "html",
        async: false,
        success: function(data){
            var temp = new Array();
            if(data != 0){
            temp = data.split(' ');
            if(temp[0] == 0){
                alert("Sản phẩm không đủ")
            }
            else if(temp[1] == 0){
                alert("Giá trị nhập vào không hợp lệ!")
            }
            else{
            $("#countShop").html(temp[0]) ;
            $("#current_cart_items_count").html(temp[0]);
            result =  temp[1];
            }
        }}
        });
    return result;
    
}
//get Add of Provider
function  getAddProvider(id){
    $.ajax({
        type: "GET",
        url: "/getAddProvider.vn",
        data: "id="+id,
        dataType: "text",
        async: false,
        success: function(data){
            return data;
        }
        });
}

//get Add of Provider
function  getAddProvider(id){
    $.ajax({
        type: "GET",
        url: "/getAddProvider.vn",
        data: "id="+id,
        dataType: "text",
        async: false,
        success: function(data){
            return data;
        }
        });
}

function  updateComment(content){
    $.ajax({
        type: "GET",
        url: "/uploadComment.vn",
        data: "content="+content,
        dataType: "html",
        async: false,
        success: function(data){
        	$("#content").val('');
        }
        });
}
function  generateXu(){
    $.ajax({
        type: "GET",
        url: "/generateXu.vn",
        dataType: "html",
        async: false,
        success: function(data){
            $("#xuActive").val(data);
        }
        });
}

function  addXu(value){
    $.ajax({
        type: "GET",
        url: "/addXu.vn",
        data: "value="+value,
        dataType: "html",
        async: false,
        success: function(data){
        	var temp = new Array();
            if(data != 0){
            temp = data.split(' ');
            if(temp[0] == '1'){
                $("#xuOnline").html(temp[1]);
                $("#valueXu").val('');
                alert('Bạn đã nạp tiền thành công! Số tiền của bạn là : '+temp[1]);
            }
            else if(temp[0] == '2'){
                alert('Bạn phải đăng nhập trước khi nạp tiền');
            }
            else if(temp[0] == '3'){
            	alert('Vui lòng nạp mã xu');
            }
            else {
            	alert('Mã Xu không đúng!');
            }
            }
        }
    });
}

function  transferXu(uid,money){
    $.ajax({
        type: "GET",
        url: "/transferXu.vn",
        data: "name="+uid+"&money="+money,
        dataType: "html",
        async: false,
        success: function(data){
        	var temp = new Array();
            if(data != 0){
            temp = data.split(' ');
            if(temp[0] == '1'){
                $("#xuOnline").html(temp[1]);
                $("#idUser").val('');
                $("#valueMoney").val('');
                alert('Bạn đã chuyển tiền thành công! Số tiền hiện tại của bạn là : '+temp[1]);
            }
            else if(temp[0]== '2'){
            	alert('Tài khoản chuyến đến không tồn tại');
            }
            else if(temp[0]== '3'){
            	alert('Số tiền trong tài khoản không đủ');
            }
            else if(temp[0] == '4'){
                alert('Bạn phải đăng nhập trước khi nạp tiền');
            }
            else if(temp[0] == '5'){
            	alert('Vui lòng nhập giá trị');
            }
            }
        }
    });
}




function  resetFormCustomer(){
    $.ajax({
        type: "GET",
        url: "/resetFormCustomer.vn",
        dataType: "html",
        async: false,
        success: function(data){
            $("#message").html('');
        }
        });
}

function resetFormProvider(type){
$.ajax({
    type: "GET",
    url: "/resetForm.vn",
    data: "type="+type,
    dataType: "html",
    async: false,
    success: function(){
        $("#message").html('');
    }
    });
}
function updateCustomerInfo() {
    var name = $('input[name=nameCustomer]').val();
    var phone = $('input[name=phone]').val();
    var mail = $('input[name=email]').val();
    var homeNumber = $('input[name=homeNumber]').val();
    var wardName = $('input[name=wardName]').val();
    var dateShip = $('input[name=dateShip]').val();
    var districtName =  $("#districtName option:selected").val();
    var streetName = $('input[name=streetName]').val();
    var buildingName = $('input[name=buildingName]').val();
    var param = "name=" + name + "&phone=" + phone + "&mail=" + mail + "&homeNumber=" + homeNumber + "&streetName="
            + streetName + "&wardName=" + wardName + "&districtName=" + districtName + "&buildingName=" + buildingName;
    $.ajax({
        type : "GET",
        url : "/updateCustomerInfo.vn",
        data : param,
        dataType : "html",
        async : false,
        success : function(data) {
            if (data == 1) {
                alert('Đã lưu thành công');
            }
    }
    });

}

/*function  addToCart(quantity,idProduct){
    $.ajax({
        type: "GET",
        url: "/addShoppingCart.vn",
        data: "id="+idProduct+"&number="+quantity,
        dataType: "html",
        async: false,
        success: function(data){
            if(data == 0){
                alert("Sản phẩm không đủ")
            }
            else{
            $("#countShop").html(data) ;
            $("#current_cart_items_count").html(data) ;
            }
        }
        });
    
}*/
/*function  uploadImage(url){
    $.ajax({
        type: "GET",
        url: "/addShoppingCart.vn",
        data: "id="+idProduct+"&number="+quantity,
        dataType: "html",
        async: false,
        success: function(data){
        
            if(data == 0){
                alert("Sản phẩm không đủ");
            }
            else{
            $("#countShop").html(temp[0]) ;
            $("#current_cart_items_count").html(temp[0]) ;
            }
        }
        });
    
}*/


function  updateNumberProduct(idUpdate,numberUpdate, index){
    $.ajax({
        type: "GET",
        url: "/updateShoppingCart.vn",
        data: "id="+idUpdate+"&number="+numberUpdate,
        dataType: "html",
//        cache: false,
        async: false,
        success: function(data){
            var temp = new Array();
            if(data != 0){
            temp = data.split(' ');
                if(temp[0] == 0){
                $("#"+index).val(temp[1]);
                }
                else{
                $("#Sub"+ index).html(temp[2]);
                $("#total").html(temp[3]);
//                $("#"+index).val() = temp[1];
                }
            }
             /* //  alert(data);
             //get value of data
              //  alert(data.id);
               // alert(data.value);
                
                if(data.id == "1"){
                    alert("Vui lòng chọn nhỏ hơn "+ data.value + " san phẩm");
                }*/
        }
            
        });
    
}




