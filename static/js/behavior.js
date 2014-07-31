YUI().use('event', function (Y) {
    var form = Y.one("form");
    var submit = Y.one(".submit");
//    alert(form);

    var firstName = Y.one("#firstname");
    var lastName = Y.one("#lastname");
    var age = Y.one("#age");
    var phone = Y.one("#phone");

    var re = /^\+\([0-9][0-9][0-9]\) [0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]$/ ;

    submit.on("click", function(e){
        var firstName_value = firstName.get('value');
        var lastName_value = lastName.get('value');
        var age_value = age.get('value');
        var phone_value = phone.get('value');

        var msg = '';

        if (firstName_value.length < 5){
            msg += "You have entered too short first name! Please, enter at least 5 symbols\n";
        }

        if (lastName_value.length < 5){
            msg += "You have entered too short last name! Please, enter at least 5 symbols\n";
        }

        if (isNaN(age_value)){
            msg += "You have entered not a valid number value for age field! Please, correct number at age field\n";
        }

        if (!re.test(phone_value)){
            msg += "You have entered not a valid phone number! Please, phone number must be entered in '+(xxx) xxx-xx-xx' format\n";
        }

        if (msg){
            alert(msg);
            e.preventDefault()
            e.stopPropagation();
        }

    });
});