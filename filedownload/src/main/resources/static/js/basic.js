
 function doAlertSubmit()
    {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(!re.test($('#email').val()))
    {
     $('#messageTest').html('Email not correct');
     $("#alertTab").show();
       return;
    }
    var pattern = new RegExp("^[0-9]{10}$");
    if(!pattern.test($('#contact').val()))
    {
     $('#messageTest').html('Contact not correct');
     $("#alertTab").show();
     return;
    }
    if($('#name').val().trim()=="")
    {
     $('#messageTest').html('Name not correct');
     $("#alertTab").show();
     return;
    }

     $.ajax({url: "/alert/save?email="+$('#email').val()+"&name="+$('#name').val()+"&contact="+$('#contact').val()+"&message="+$('#message').val(), success: function(result){
        $('#enquirypop').hide();
     }});
    }