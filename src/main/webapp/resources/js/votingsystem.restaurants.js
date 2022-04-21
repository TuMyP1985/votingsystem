const restaurantAjaxUrl = "votingsys/restaurant/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: restaurantAjaxUrl,
    updateTable: function () {
        window.location = "restaurants"; //переход вместо обновления опять формируем (переходим) на страницу restaurant

   }
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {"data": "name"},
                {},
                {},
                {
                    "defaultContent": "Delete",
                    "orderable": false
                },
                {}
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});