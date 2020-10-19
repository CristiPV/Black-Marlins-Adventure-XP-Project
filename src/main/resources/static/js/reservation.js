$('document').ready(function() {

    $('table #readInfoButton').on('click', function(event) {
        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function(reservation, status) {
            $('#id').val(reservation.id);
            $('#ActName').val(reservation.activity.name);
            $('#date').val(reservation.date);
            $('#price').val(reservation.price);
            $('#duration').val(reservation.hours);
            $('#customerFName').val(reservation.customer.firstName);
            $('#customerLName').val(reservation.customer.lastName);
        });

        $('#infoModal').modal();
    });

});
