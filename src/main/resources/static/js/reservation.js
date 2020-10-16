$('document').ready(function() {

    $('table #deleteButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();
    });

    $('table #cancelButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmCancelButton').attr('href', href);
        $('#cancelModal').modal();
    });
});