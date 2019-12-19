// ..... ENABLE BUTTON AGREE AND DISAGREE IN FORM

$('#agreeBtn').click(function(){
    $('#downloadButton').attr('disabled', false);
});

$('#disagreeBtn').click(function(){
    $('#downloadButton').attr('disabled', true);
});

// ..... ADD CLASS TO SELECT BUTTONS

$('select').addClass('selectpicker');

$(document).ready(function(){
    var options={
        format: 'mm/dd/yyyy',
        todayHighlight: true,
        autoclose: true,
    };

    $('.datepicker1').each(function(){
        $(this).datepicker(options);
    });
})
