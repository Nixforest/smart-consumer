// Generated by CoffeeScript 1.3.3
/*
Copyright (c) 2012 [DeftJS Framework Contributors](http://deftjs.org)
Open source under the [MIT License](http://en.wikipedia.org/wiki/MIT_License).
*/

/**
A mixin that creates and attaches the specified view controller(s) to the target view.

Used in conjunction with {@link Deft.mvc.ViewController}.
*/

Ext.define('Deft.mixin.Controllable', {});

Ext.Class.registerPreprocessor('controller', function(Class, data, hooks, callback) {
  var controller, controllers, parameters, self;
  if (arguments.length === 3) {
    parameters = Ext.toArray(arguments);
    hooks = parameters[1];
    callback = parameters[2];
  }
  if ((data.mixins != null) && Ext.Array.contains(data.mixins, Ext.ClassManager.get('Deft.mixin.Controllable'))) {
    controller = data.controller;
    delete data.controller;
    controllers = [];
    if (controller != null) {
      controllers = Ext.isArray(controller) ? controller : [controller];
    }
    Class.prototype.constructor = Ext.Function.createSequence(Class.prototype.constructor, function() {
      var controllerClass, _i, _len;
      for (_i = 0, _len = controllers.length; _i < _len; _i++) {
        controllerClass = controllers[_i];
        try {
          Ext.create(controllerClass, {
            view: this
          });
        } catch (error) {
          Deft.Logger.warn("Error initializing Controllable instance: an error occurred while creating an instance of the specified controller: '" + controllerClass + "'.");
          throw error;
        }
      }
    });
    if (controllers.length > 0) {
      self = this;
      Ext.require(controllers, function() {
        if (callback != null) {
          callback.call(self, Class, data, hooks);
        }
      });
      return false;
    }
  }
});

Ext.Class.setDefaultPreprocessorPosition('controller', 'before', 'mixins');
