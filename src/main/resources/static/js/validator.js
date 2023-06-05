function Validator(option){

    var selectorRules = {}
    function validate(inputElement, rule){
        var rules = selectorRules[rule.selector];
        var errorMessage
        var errorElement = inputElement.closest(option.formGroupSelector).querySelector(option.errorSelector)
        for(let i = 0; i<rules.length;i++){
            errorMessage = rules[i](inputElement.value);
            if(errorMessage) break;
        }

        if(errorMessage){
            errorElement.innerText = errorMessage;
            inputElement.classList.add('invalid')

        }else{
            errorElement.innerText=''
            inputElement.classList.remove('invalid')
        }
        return !errorMessage
    }
    var formElement = document.querySelector(option.form)
    if(formElement){
        formElement.onsubmit = function(e){
            e.preventDefault();
            var isSubmit = true
            option.rules.forEach((rule) => {
                var inputElement =  formElement.querySelector(rule.selector);
                // console.log(inputElement)
                var isValid = validate(inputElement,rule);
                if(!isValid){
                    isSubmit = false;
                }

            });
            if(isSubmit){
                formElement.submit();
            }
        }
    }

    option.rules.forEach(rule => {
        var inputElement =  formElement.querySelector(rule.selector);
        // console.log(inputElement)
        if(Array.isArray(selectorRules[rule.selector])){
            selectorRules[rule.selector].push(rule.test);
        }else{
            selectorRules[rule.selector]=[rule.test];
        }

        if(inputElement){
            inputElement.onblur = function() {
                validate(inputElement,rule);
            }

            inputElement.oninput = function(){
                var errorElement = inputElement.closest(option.formGroupSelector).querySelector(option.errorSelector)
                errorElement.innerText=''
                inputElement.classList.remove('invalid')
            }
        }
    });
}

Validator.isRequired = function(selector,message) {
    return {
        selector: selector,
        test : function(value){
            return value?undefined:message||"Vui lòng nhập trường này.";
        }
    }
}

Validator.isEmail = function(selector,message){
    return {
        selector:selector,
        test: (value)=>{
            var regex =/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
            return regex.test(value)?undefined:message||"Email không hợp lệ.";
        }
    }
    
}

Validator.isEqualOld = function(selector,str, message){
    return {
        selector:selector,
        test: (value)=>{
            return value.toLowerCase()!=str.toLowerCase()?undefined:message||"Tên thể loại chưa thay đổi.";
        }
    }

}
Validator.minLength = function(selector,length,message){
    return {
        selector:selector,
        test: (value)=>{
            return value.length>=length?undefined:message||"Vui lòng nhập tối thiểu là "+length+" kí tự";
        }
    }
}
Validator.isConfirmed = function(selector, str, message){
    return {
        selector:selector,
        test:(value)=>{
            return str()==value?undefined:message||"Mật khẩu nhập lại không đúng.";
        }
    }
}
Validator.minValue = function(selector, intValue, message) {
    return {
        selector: selector,
        test: (value) => {
            return value >= intValue ? undefined : message || "Giá trị nhập vào phải lớn hơn 0.";
        }
    }
}