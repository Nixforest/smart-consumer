function validateProviderRegister() {
    var flag = true;
    var temp = false;
    $("#headerFocus").focus();
      if($('input[name=loginId]').val() == ""){
          $('input[name=loginId]').css("background", "#FC0");
         if(temp == false){
             $('input[name=loginId]').focus();
             temp = true;
         }
      }
      if($('input[name=loginPassword]').val() == ""){
          $('input[name=loginPassword]').css("background", "#FC0");
         if(temp == false){
             $('input[name=loginPassword]').focus();
             temp = true;
         }
      }
      if($('input[name=loginPasswordPre]').val() == ""){
          $('input[name=loginPasswordPre]').css("background", "#FC0");
         if(temp == false){
             $('input[name=loginPasswordPre]').focus();
             temp = true;
         }
      }
      if($('input[name=loginName]').val() == ""){
          $('input[name=loginName]').css("background", "#FC0");
         if(temp == false){
             $('input[name=loginName]').focus();
             temp = true;
         }
      }
      if($('input[name=email]').val() == ""){
          $('input[name=email]').css("background", "#FC0");
         if(temp == false){
             $('input[name=email]').focus();
             temp = true;
         }
      }
      if($('input[name=homeNumber]').val() == ""){
          $('input[name=homeNumber]').css("background", "#FC0");
         if(temp == false){
             $('input[name=homeNumber]').focus();
             temp = true;
         }
      }
      if($('input[name=streetName]').val() == ""){
          $('input[name=streetName]').css("background", "#FC0");
         if(temp == false){
             $('input[name=streetName]').focus();
             temp = true;
         }
      }
      if($('input[name=wardName]').val() == ""){
          $('input[name=wardName]').css("background", "#FC0");
         if(temp == false){
             $('input[name=wardName]').focus();
             temp = true;
         }
      }
      if($('input[name=phone]').val() == ""){
          $('input[name=phone]').css("background", "#FC0");
         if(temp == false){
             $('input[name=phone]').focus();
             temp = true;
         }
      }
      if($('textarea').val()==""){
          $('textarea').css("background", "#FC0");
          if(temp == false){
              $('textarea').focus();
              temp = true;
          } 
      }
      if($("#price option:selected").val() == '0'){
          $('#price option:selected').css("background", "#FC0");
          if(temp == false){
              $('#price option:selected').focus();
              temp = true;
          } 
      }
      if(temp == true){
          flag = false;
          alert('Vui lòng nhập các thông tin yêu cầu!');
      }
      
      return flag;
                         

}

function validateCheckoutInfo() {
    var flag = true;
    var temp = false;
    if($('input[name=nameCustomer]').val() == ""){
        $('input[name=nameCustomer]').css("background", "#FC0");
       if(temp == false){
           $('input[name=nameCustomer]').focus();
           temp = true;
       }
    }
    if($('input[name=phone]').val() == ""){
        $('input[name=phone]').css("background", "#FC0");
       if(temp == false){
           $('input[name=phone]').focus();
           temp = true;
       }
    }
    if($('input[name=email]').val() == ""){
        $('input[name=email]').css("background", "#FC0");
       if(temp == false){
           $('input[name=email]').focus();
           temp = true;
       }
    }
    if($('input[name=homeNumber]').val() == ""){
        $('input[name=homeNumber]').css("background", "#FC0");
       if(temp == false){
           $('input[name=homeNumber]').focus();
           temp = true;
       }
	}
    if($('input[name=streetName]').val() == ""){
        $('input[name=streetName]').css("background", "#FC0");
       if(temp == false){
           $('input[name=streetName]').focus();
           temp = true;
       }
    }
    if($('input[name=wardName]').val() == ""){
        $('input[name=wardName]').css("background", "#FC0");
       if(temp == false){
           $('input[name=wardName]').focus();
           temp = true;
       }
    }
    if($('input[name=dateShip]').val() == ""){
        $('input[name=dateShip]').css("background", "#FC0");
       if(temp == false){
           $('input[name=dateShip]').focus();
           temp = true;
       }
    }
    if($('textarea').val()==""){
        $('textarea').css("background", "#FC0");
        if(temp == false){
            $('textarea').focus();
            temp = true;
        } 
    }
    if($("#districtName option:selected").val() == '0'){
        $('#districtName option:selected').css("background", "#FC0");
        if(temp == false){
            $('#districtName option:selected').focus();
            temp = true;
        } 
    }
    if(temp == true){
        flag = false;
        $("#message").html('');
        alert('Vui lòng nhập các thông tin yêu cầu!');
    }
    return flag;
}
// start Provider login 04/02/2012
function validateProviderLogin() {
    var flag = true;
    var temp = false;
    $("#headerFocus").focus();
    if($('input[name=loginId]').val() == ""){
        $('input[name=loginId]').css("background", "#FC0");
        if(temp == false){
            $('input[name=loginId]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=loginPassword]').val() == ""){
        $('input[name=loginPassword]').css("background", "#FC0");
        if(temp == false){
            $('input[name=loginPassword]').focus();
            temp = true;
        } 
    }
    if(temp == true){
        flag = false;
        $("#message").html('');
        alert('Vui lòng nhập các thông tin yêu cầu!');
    }
    
    return flag;
    
}
//end  Provider login 04/02/2012

//start voucher Form
function validateVoucherForm() {
    var flag = true;
    var temp = false;
    if($('input[name=phone]').val() == ""){
        $('input[name=phone]').css("background", "#FC0");
        if(temp == false){
            $('input[name=phone]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=homeNumber]').val() == ""){
        $('input[name=homeNumber]').css("background", "#FC0");
        if(temp == false){
            $('input[name=homeNumber]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=streetName]').val() == ""){
        $('input[name=streetName]').css("background", "#FC0");
        if(temp == false){
            $('input[name=streetName]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=wardName]').val() == ""){
        $('input[name=wardName]').css("background", "#FC0");
        if(temp == false){
            $('input[name=wardName]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=districtName]').val() == ""){
        $('input[name=districtName]').css("background", "#FC0");
        if(temp == false){
            $('input[name=districtName]').focus();
            temp = true;
        } 
    }
    
    if(temp == true){
        flag = false;
        //$("#message").html('');
        alert('Vui lòng nhập các thông tin yêu cầu!');
    }
    
    return flag;
    
}
//end Voucher Form
//start Customer register 04/02/2012
function validateCustomerRegister() {
    var flag = true;
    var temp = false;
    $("#headerHome").focus();
    if($('#loginIdR').val() == ""){
        $('#loginIdR').css("background", "#FC0");
        if(temp == false){
            $('#loginIdR').focus();
            temp = true;
        } 
    }
    
    if($('#loginPasswordR').val() == ""){
        $('#loginPasswordR').css("background", "#FC0");
        if(temp == false){
            $('#loginPasswordR').focus();
            temp = true;
        } 
    }
    
    if($('#loginPasswordPreR').val() == ""){
        $('#loginPasswordPreR').css("background", "#FC0");
        if(temp == false){
            $('#loginPasswordPreR').focus();
            temp = true;
        } 
    }
    
    if($('#loginPhoneR').val() == ""){
        $('#loginPhoneR').css("background", "#FC0");
        if(temp == false){
            $('#loginPhoneR').focus();
            temp = true;
        } 
    }
    
    if($('#loginEmailR').val() == ""){
        $('#loginEmailR').css("background", "#FC0");
        if(temp == false){
            $('#loginEmailR').focus();
            temp = true;
        } 
    }
   /* if($('input[name=districtNameR]').val() == ""){
        $('input[name=districtNameR]').css("background", "#FC0");
        if(temp == false){
            $('input[name=districtNameR]').focus();
            temp = true;
        } 
    }*/
    
    if(temp == true){
        flag = false;
        $("#message").html('');
        alert('Vui lòng nhập các thông tin yêu cầu!');
    }
    
    return flag;
    
}
//end   Customer register 04/02/2012

//start  Provider uploadFood 04/02/2012
function validateProviderFood() {
    var flag = true;
    var temp = false;
    $("#headerFocus").focus();
    
    
    if($('input[name=name]').val() == ""){
        $('input[name=name]').css("background", "#FC0");
        if(temp == false){
            $('input[name=name]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=number]').val() == "0"){
        $('input[name=number]').css("background", "#FC0");
        if(temp == false){
            $('input[name=number]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=price]').val() == "0"){
        $('input[name=price]').css("background", "#FC0");
        if(temp == false){
            $('input[name=price]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=promoPrice]').val() == "0"){
        $('input[name=promoPrice]').css("background", "#FC0");
        if(temp == false){
            $('input[name=promoPrice]').focus();
            temp = true;
        } 
    }
    
    if($('textarea[name=detail]').val() == ""){
        $('textarea[name=detail]').css("background", "#FC0");
        if(temp == false){
            $('textarea[name=detail]').focus();
            temp = true;
        } 
    }
    
    if($('input[name=url]').val() == ""){
        $('input[name=url]').css("background", "#FC0");
        if(temp == false){
            temp = true;
        } 
    }
    if(temp == true){
        flag = false;
        
        alert('Vui lòng nhập các thông tin yêu cầu!');
    }
    
    return flag;
    
}

function checkMaxLength(text, len) {
    if(text.length >= number){
        return true;
    }
    return false;
}

function checkNumber(number) {
    if(number >=0){
        return true;
    }
    return false;
}

function checkNumber1(number) {
    if(number >0){
        return true;
    }
    return false;
}

