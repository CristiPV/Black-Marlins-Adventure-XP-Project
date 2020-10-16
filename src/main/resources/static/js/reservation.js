$('document').ready(function() {

    $('table #deleteButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();
    });

    $('table #readReservationInfoButton').on('click', function(event) {
        event.preventDefault();

        var href = $(this).attr('href');

        $('#reservationInfoModal').modal();

        $.get(href, function(reservation, status) {
            $('#id').val(reservation.id);
            //$('#customerPhone').val(reservation.customerPhone);
            //$('#reservationPrice').val(reservation.price);
            //$('#activityDescription').val(reservation.activityDescription);
        });


    });

});