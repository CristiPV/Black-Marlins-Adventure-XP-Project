$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function(activity, status) {
            $('#idEdit').val(activity.id);
            $('#descriptionEdit').val(activity.description);
            $('#nameEdit').val(activity.name);
            $('#ageLimitEdit').val(activity.ageLimit);
            $('#hourlyPriceEdit').val(activity.hourlyPrice);
        });

        $('#editModal').modal();
    });

    $('table #readInfoButton').on('click', function(event) {
            event.preventDefault();

            var href = $(this).attr('href');

            $.get(href, function(activity, status) {
                $('#id').val(activity.id);
                $('#description').val(activity.description);
                $('#name').val(activity.name);
            });

            $('#infoModal').modal();
        });



    $('table #deleteButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();
    });

    $('table #pauseButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmPauseButton').attr('href', href);
        $('#pauseModal').modal();
    });

    $('table #unpauseButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmUnpauseButton').attr('href', href);
        $('#unpauseModal').modal();
    });

});
