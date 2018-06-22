/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var theTotal;
var candyToVend;
var candyPriceToVend;


$(document).ready(function () {
    loadVendingItems();
    sellItem();
    insertMoney();
    changeMaker();

});



function loadVendingItems() {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items/',
        success: function (itemArray) {
            $('#itemDivs').empty();
            $.each(itemArray, function(index, item){
                
                var allItems = '<div id ="item' + item.id+ '"class="itemStyle col-md-3" style="margin: 3%">';
                allItems += '<p class="id" class="text-left itemId">' + item.id + '</p>';
                allItems += '<p class="name" class="text-center itemName">' + item.name + '</p>';
                allItems += '<p class="price" class="text-center itemPrice">$' + item.price.toFixed(2) +'</p>';
                allItems += '<p class="quantity" class="text-center itemQuantity">' + 'Quanity Left: ' +
                    item.quantity + '</p>' + '</div>';

                $('#itemDivs').append(allItems);
            });

                    $('.itemStyle').hover(
                    function () {
                        $(this).css('background-color', 'white');
                    }, function () {
                        $(this).css('background-color', 'pink');
                    });

                    $('.itemStyle').click(function() {
                        candyToVend = $(this).find('.id').text();                       
                        candyPriceToVend = $(this).find('.price').text();
                        $('#itemDisplay').val(candyToVend);
                        $('#messageDisplay').removeAttr('placeholder');
                        $('#changeDisplay').removeAttr('placeholder');
        });
    },
        error: function() {
            alert('fail to load items, check server');
        }
    });           

}



function insertMoney() {
    theTotal = 0;
    $('button').click(function () {
        theTotal = Number(theTotal) + Number($(this).val());
        $('.moneyBox').text(theTotal.toFixed(2));
    });

    $('.moneyBox').text(theTotal.toFixed(2));
}

function sellItem() {
    

    $('#purchaseButton').click(function () {

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/money/' + theTotal.toFixed(2) + '/item/' + candyToVend,
            success: function (change) {
                $('#displayMessage').val('Thankyou!!!');
                $('.moneyBox').empty();
                $('#changeDisplay').val(
                    change.quarters + ' Quarters \n' +
                    change.dimes + ' Dimes \n' +
                    change.nickels + ' Nickels \n' +
                    change.pennies + ' Pennies');

                $('#changeDisplay').removeAttr('placeholder');
                $('#itemDisplay').removeAttr('placeholder');
                loadVendingItems();
                theTotal = 0;

            },
            error: function (error) {
                $('#changeDisplay').removeAttr('placeholder');
                $('#displayMessage').val(error.responseJSON.message);
            }
        });
    });
}


function changeMaker() {

    $('#changeButton').on('click', function () {
        $('#messageDisplay').removeAttr('placeholder');

        var cashToChange =Math.floor(theTotal * 100);
        
        var quarters = Math.floor(cashToChange / 25);
        cashToChange = Math.floor(cashToChange % 25);

        var dimes = Math.floor(cashToChange / 10);
        cashToChange = Math.floor(cashToChange % 10);

        var nickels = Math.floor(cashToChange / 5);
        cashToChange = Math.floor(cashToChange % 5);

        var pennies = Math.floor(cashToChange);

        $('#changeDisplay').val(
            quarters + ' Quarters\n' +
            dimes + ' Dimes\n' +
            nickels + ' Nickels\n' +
            pennies +  ' Pennies\n');
            
        $('#displayMessage').removeAttr('placeholder');
        theTotal=0;

    });
}