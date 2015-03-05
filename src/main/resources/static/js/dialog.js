"use strict";

var BsDialog = (function() {

    var module = {},
        modal;

    var init = function(config) {
        modal = config.modal;
        var modalShowEventHandler = config.modalShowEventHandler;
        var modalHideEventHandler = config.modalHideEventHandler || reset;

        console.log(modalShowEventHandler);
        console.log(modalHideEventHandler);

        reset();

        modal.on('show.bs.modal', function (event) {
            modalShowEventHandler(module, event)
        });

        modal.on('hide.bs.modal', function (event) {
            modalHideEventHandler(module, event);
        });
    };

    var reset = function() {
        setTitle('Loading...');
        setContent('Loading...');
    };

    var setTitle = function(title) {
        modal.find('.modal-title').text(title);
    };

    var setContent = function(content) {
        modal.find('.modal-body > .content').text(content);
    };

    return module = {
        init: init,
        reset: reset,
        setTitle: setTitle,
        setContent: setContent
    };
})();