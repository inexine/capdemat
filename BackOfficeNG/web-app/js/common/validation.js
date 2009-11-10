(function() {
  var zc = zenexity.capdemat;
  var zct = zenexity.capdemat.tools;
  var yud = YAHOO.util.Dom;
  var yus = YAHOO.util.Selector;
  var yl = YAHOO.lang;
  var yu = YAHOO.util;
  var yue = YAHOO.util.Event;

  var me = zc.Validation = function() {

    var visible = function (el) {
      while (el.nodeName.toLowerCase() != 'body'
          && el.style.display.toLowerCase() != 'none'
          && el.style.visibility.toLowerCase() != 'hidden'
          && !yud.hasClass(el, 'unactive')) {
        el = el.parentNode;
      }
      if (el.nodeName.toLowerCase() == 'body') return true;
      return false;
    }

    var scopePolicy = {
      IGNORE: {
        value: 'IGNORE',
        rootEl: function(el) { if (el.nodeName !== 'FORM') el = el.form; return el; },
        filter: function(el) { return visible(el); }
      },
      INSIDE: {
        value: 'INSIDE',
        rootEl: function(el) {
          if (!yud.hasClass(el, 'validation-scope')) el = yud.getAncestorByClassName(el,'validation-scope');
          return el;
        },
        filter: function(el) { return visible(el); }
      },
      OUTSIDE: {
        value: 'OUTSIDE',
        rootEl: function(el) { if (el.nodeName !== 'FORM') el = el.form; return el; },
        filter: function(el) { if (yud.getAncestorByClassName(el, 'validation-scope') === null && visible(el)) return true; return false; }
      }
    };

    return {

      scope: { IGNORE:'IGNORE', INSIDE:'INSIDE', OUTSIDE:'OUTSIDE'},

      rule: function(type, value) {
        this.type = type;
        this[type] = value;
      },

      rules: {},

      putRules: function (rls) {
        for(name in rls) me.rules[name] = rls[name];
      },

      complexRule: function(func, errorMsg) {
        this.func = func;
        this.fieldsList = [];
        this.errorMsg = !yl.isUndefined(errorMsg) ? errorMsg : '';
        this.pushFields = function(){ this.fieldsList.push(arguments) };
        this.check = function(fieldNames){
          var fields = [];
          for (i=0; i<fieldNames.length; i++) {
            if (!yl.isUndefined(me.fields[fieldNames[i]])) fields.push(me.fields[fieldNames[i]]);
            else return true;
          }
          return this.func.apply(me, fields);
        };
      },

      complexRules: {},

      putComplexRules: function (rls) {
        for(name in rls) me.complexRules[name] = rls[name];
      },

      fields: {},

      fieldText: function(el) {
        var props = {'maxLength': el.maxLength};
        if (!!el.getAttribute('regex')) props.regex = RegExp(el.getAttribute('regex'));
        return props ;
      },

      fieldSelect: function(el) {
        return {'selectedIndex': el.selectedIndex};
      },

      // TODO - manage multi-valued field
      // FIXME - remanme
      fieldCheck: function(el, sameNameField) {
        var props = {
          'value': el.value,
          'checked': el.checked,
          'enhanceErrorEl': yud.getAncestorByTagName(el,'ul')
        };
        if (sameNameField != undefined) {
          props.value = el.ckecked ? el.value : sameNameField.value;
          props.checked = el.checked || sameNameField.checked;
        }
        return props;
      },

      field: function(el, type) {
        this.enhanceErrorEl = el;
        this.valid = true;
        this.value = el.value;
        this.errorMsg = el.title;
        this.required = yud.hasClass(el, 'required');
        this.rule = /validate-([\w-]+)/i.test(el.className) ? /validate-([\w-]+)/i.exec(el.className)[1] : ''

        var props = zct.tryToCall(me['field'+zct.capitalize(type)], me, el, me.fields[el.name]);
        if (!!props)
          for(name in props)
            this[name] = props[name];
      },

      fetchFields: function(e, scope) {
        me.fields = {};
        yue.preventDefault(e);
        var target = e.nodeName ? e : yue.getTarget(e);
        var els = [], filter = scopePolicy[scope].filter, root = scopePolicy[scope].rootEl(target);
        els = els.concat(yud.getElementsBy(filter, 'input', root),
            yud.getElementsBy(filter, 'textarea', root),
            yud.getElementsBy(filter, 'select', root));

        zct.each(els, function(){
          var nodeName = this.nodeName.toLowerCase();
          if (nodeName === 'select')
            me.fields[this.name] = new me.field(this,'select');
          else if (nodeName === 'input' && zct.isIn(this.type,['text', 'password']))
            me.fields[this.name] = new me.field(this,'text');
          else if (nodeName === 'input' && zct.isIn(this.type,['radio', 'checkbox']))
            me.fields[this.name] = new me.field(this,'check');
          else if (nodeName === 'textarea')
            me.fields[this.name] = new me.field(this,'text');
        });
      },

      cleanErrors: function(e, errorsEl) {
        zct.each(me.fields, function(){
          yud.removeClass(this.enhanceErrorEl, 'validation-failed');
        });
        zct.html(errorsEl, '');
      },

      displayErrors: function(errorsEl, errorMsgs) {
        zct.html(errorsEl, errorMsgs.join('').length > 0 ? errorMsgs.join('<br />') : 'Des champs obligatoires ne sont pas correctement remplis, merci de v&eacute;rifier les champs en rouge');
      },

      checkComplexRules: function(errorMsgs) {
        var valid = true
        zct.each(me.complexRules, function(){
          var cRule = this
          zct.each(cRule.fieldsList, function(){
            cValid = cRule.check(this);
            valid = valid && cValid;
            if (!cValid){
              for (i=0; i<this.length; i++)
                yud.addClass(me.fields[this[i]].enhanceErrorEl, 'validation-failed');
              if (cRule.errorMsg.length > 0) errorMsgs.push(cRule.errorMsg);
            }
          });
        });
        return valid;
      },

      check: function(e, errorsEl, scope) {
        var valid = true, errorMsgs=[];
        me.cleanErrors(e, errorsEl);
        me.fetchFields(e, (!yl.isUndefined(scope) ? scope : me.scope.IGNORE));

        zct.each(me.fields, function(){
          var rule = me.rules[this.rule];
          if (this.required && this.value.length === 0) {
            this.valid = false;
          } else if (!yl.isUndefined(rule) && this.value.length > 0) {
            if (rule.type === 'regex') this.valid = rule[rule.type].test(this.value);
            if (rule.type === 'func') this.valid = zct.tryToCall(rule[rule.type], me, this, arguments[0]);
          }
          valid = valid && this.valid;
          if (!this.valid) {
            yud.addClass(this.enhanceErrorEl, 'validation-failed');
            if (this.errorMsg.length > 0) errorMsgs.push(this.errorMsg);
          }
        });

        valid = me.checkComplexRules(errorMsgs) && valid;
        if (!valid) me.displayErrors(errorsEl, errorMsgs);
        return valid;
      }
    };

  }();
  
  me.putRules({
    'number': new me.rule('func',function(f){ return (!isNaN(f.value) || !f.value.match(/[^\d]/)); }),
    'digits': new me.rule('func',function(f){ return !(f.value.replace(/ /,'').match(/[^\d]/)); }),
    'label': new me.rule('func',function(f){ return (f.value.length > 0); } ), // useless
    'alpha': new me.rule('regex', /^[a-zA-Z]+$/),
    'alphanum': new me.rule('regex', /\W/), //false
    'date': new me.rule('func', function(f){ return !isNaN(new Date(f.value)); }),
    'email': new me.rule('regex', /\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/),
    'url': new me.rule('regex', /^(http|https|ftp):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i),
    'date-au':  new me.rule('regex', /^(\d{1}|\d{2})\/(\d{1}|\d{2})\/(\d{4})$/),
    'currency-dollar': new me.rule('regex', /^\$?\-?([1-9]{1}[0-9]{0,2}(\,[0-9]{3})*(\.[0-9]{0,2})?|[1-9]{1}\d*(\.[0-9]{0,2})?|0(\.[0-9]{0,2})?|(\.[0-9]{1,2})?)$/),
    'one-required': new me.rule('func', function(f){ return f.checked; }),
    'not-first': new me.rule('func', function(f){ return f.selectedIndex > 0; }),
    'not-empty': new me.rule('func', function(f){ return (f.selectedIndex > 0 && f.value.length > 0); }),
    'regex': new me.rule('func', function(f){ return f.regex.test(f.value); }),
    /* ex capdemat specific rules */
    'string': new me.rule('func', function(f){ return true; }),
    'token': new me.rule('func', function(f){ return true; }),
    'positiveInteger': new me.rule('func',function(f){ return (!isNaN(f.value) || !f.value.match(/\D/)); } ),
    'long': new me.rule('func',function(f){ return (!isNaN(f.value) || !f.value.match(/\D/)); } ),
    'postalCode': new me.rule('regex', /^[0-9]{5}$/),
    'departmentCode': new me.rule('regex', /^[0-9]{2}$/),
    'phone': new me.rule('regex', /^0[1-9][0-9]{8}$/),
    'mobilePhone': new me.rule('regex', /^0[67][0-9]{8}$/),
    'city': new me.rule('regex', /^.{0,32}$/),
    'firstName': new me.rule('regex', /^\D{0,38}$/),
    'lastName': new me.rule('regex', /^\D{0,38}$/)
  });

  me.putComplexRules({
    'atLeastOne': new me.complexRule(function(){
      var values = '';
      for (i=0; i<arguments.length; i++)
          values += arguments[i].value;
      return values.length > 0;
    })
  });

}());
