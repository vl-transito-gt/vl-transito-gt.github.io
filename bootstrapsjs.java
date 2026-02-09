if ("undefined" == typeof jQuery)
    throw new Error("Bootstrap's JavaScript requires jQuery");
+function(n) {
    "use strict";
    var t = n.fn.jquery.split(" ")[0].split(".");
    if (t[0] < 2 && t[1] < 9 || 1 == t[0] && 9 == t[1] && t[2] < 1 || t[0] > 3)
        throw new Error("Bootstrap's JavaScript requires jQuery version 1.9.1 or higher, but lower than version 4");
}(jQuery),
+function(n) {
    "use strict";
    function t() {
        var i = document.createElement("bootstrap"), t = {
            WebkitTransition: "webkitTransitionEnd",
            MozTransition: "transitionend",
            OTransition: "oTransitionEnd otransitionend",
            transition: "transitionend"
        }, n;
        for (n in t)
            if (void 0 !== i.style[n])
                return {
                    end: t[n]
                };
        return !1
    }
    n.fn.emulateTransitionEnd = function(t) {
        var r = !1, u = this, i;
        n(this).one("bsTransitionEnd", function() {
            r = !0
        });
        return i = function() {
            r || n(u).trigger(n.support.transition.end)
        }
        ,
        setTimeout(i, t),
        this
    }
    ,
    n(function() {
        n.support.transition = t(),
        n.support.transition && (n.event.special.bsTransitionEnd = {
            bindType: n.support.transition.end,
            delegateType: n.support.transition.end,
            handle: function(t) {
                if (n(t.target).is(this))
                    return t.handleObj.handler.apply(this, arguments)
            }
        })
    })
}(jQuery),
+function(n) {
    "use strict";
    function u(i) {
        return this.each(function() {
            var u = n(this)
              , r = u.data("bs.alert");
            r || u.data("bs.alert", r = new t(this)),
            "string" == typeof i && r[i].call(u)
        })
    }
    var r = '[data-dismiss="alert"]', t = function(t) {
        n(t).on("click", r, this.close)
    }, i;
    t.VERSION = "3.3.7",
    t.TRANSITION_DURATION = 150,
    t.prototype.close = function(i) {
        function e() {
            r.detach().trigger("closed.bs.alert").remove()
        }
        var f = n(this), u = f.attr("data-target"), r;
        u || (u = f.attr("href"),
        u = u && u.replace(/.*(?=#[^\s]*$)/, "")),
        r = n("#" === u ? [] : u),
        i && i.preventDefault(),
        r.length || (r = f.closest(".alert")),
        r.trigger(i = n.Event("close.bs.alert")),
        i.isDefaultPrevented() || (r.removeClass("in"),
        n.support.transition && r.hasClass("fade") ? r.one("bsTransitionEnd", e).emulateTransitionEnd(t.TRANSITION_DURATION) : e())
    }
    ,
    i = n.fn.alert,
    n.fn.alert = u,
    n.fn.alert.Constructor = t,
    n.fn.alert.noConflict = function() {
        return n.fn.alert = i,
        this
    }
    ,
    n(document).on("click.bs.alert.data-api", r, t.prototype.close)
}(jQuery),
+function(n) {
    "use strict";
    function r(i) {
        return this.each(function() {
            var u = n(this)
              , r = u.data("bs.button")
              , f = "object" == typeof i && i;
            r || u.data("bs.button", r = new t(this,f)),
            "toggle" == i ? r.toggle() : i && r.setState(i)
        })
    }
    var t = function(i, r) {
        this.$element = n(i),
        this.options = n.extend({}, t.DEFAULTS, r),
        this.isLoading = !1
    }, i;
    t.VERSION = "3.3.7",
    t.DEFAULTS = {
        loadingText: "loading..."
    },
    t.prototype.setState = function(t) {
        var r = "disabled"
          , i = this.$element
          , f = i.is("input") ? "val" : "html"
          , u = i.data();
        t += "Text",
        null == u.resetText && i.data("resetText", i[f]()),
        setTimeout(n.proxy(function() {
            i[f](null == u[t] ? this.options[t] : u[t]),
            "loadingText" == t ? (this.isLoading = !0,
            i.addClass(r).attr(r, r).prop(r, !0)) : this.isLoading && (this.isLoading = !1,
            i.removeClass(r).removeAttr(r).prop(r, !1))
        }, this), 0)
    }
    ,
    t.prototype.toggle = function() {
        var t = !0, i = this.$element.closest('[data-toggle="buttons"]'), n;
        i.length ? (n = this.$element.find("input"),
        "radio" == n.prop("type") ? (n.prop("checked") && (t = !1),
        i.find(".active").removeClass("active"),
        this.$element.addClass("active")) : "checkbox" == n.prop("type") && (n.prop("checked") !== this.$element.hasClass("active") && (t = !1),
        this.$element.toggleClass("active")),
        n.prop("checked", this.$element.hasClass("active")),
        t && n.trigger("change")) : (this.$element.attr("aria-pressed", !this.$element.hasClass("active")),
        this.$element.toggleClass("active"))
    }
    ,
    i = n.fn.button,
    n.fn.button = r,
    n.fn.button.Constructor = t,
    n.fn.button.noConflict = function() {
        return n.fn.button = i,
        this
    }
    ,
    n(document).on("click.bs.button.data-api", '[data-toggle^="button"]', function(t) {
        var i = n(t.target).closest(".btn");
        r.call(i, "toggle"),
        n(t.target).is('input[type="radio"], input[type="checkbox"]') || (t.preventDefault(),
        i.is("input,button") ? i.trigger("focus") : i.find("input:visible,button:visible").first().trigger("focus"))
    }).on("focus.bs.button.data-api blur.bs.button.data-api", '[data-toggle^="button"]', function(t) {
        n(t.target).closest(".btn").toggleClass("focus", /^focus(in)?$/.test(t.type))
    })
}(jQuery),
+function(n) {
    "use strict";
    function r(i) {
        return this.each(function() {
            var f = n(this)
              , r = f.data("bs.carousel")
              , u = n.extend({}, t.DEFAULTS, f.data(), "object" == typeof i && i)
              , e = "string" == typeof i ? i : u.slide;
            r || f.data("bs.carousel", r = new t(this,u)),
            "number" == typeof i ? r.to(i) : e ? r[e]() : u.interval && r.pause().cycle()
        })
    }
    var t = function(t, i) {
        this.$element = n(t),
        this.$indicators = this.$element.find(".carousel-indicators"),
        this.options = i,
        this.paused = null,
        this.sliding = null,
        this.interval = null,
        this.$active = null,
        this.$items = null,
        this.options.keyboard && this.$element.on("keydown.bs.carousel", n.proxy(this.keydown, this)),
        "hover" == this.options.pause && !("ontouchstart"in document.documentElement) && this.$element.on("mouseenter.bs.carousel", n.proxy(this.pause, this)).on("mouseleave.bs.carousel", n.proxy(this.cycle, this))
    }, u, i;
    t.VERSION = "3.3.7",
    t.TRANSITION_DURATION = 600,
    t.DEFAULTS = {
        interval: 5e3,
        pause: "hover",
        wrap: !0,
        keyboard: !0
    },
    t.prototype.keydown = function(n) {
        if (!/input|textarea/i.test(n.target.tagName)) {
            switch (n.which) {
            case 37:
                this.prev();
                break;
            case 39:
                this.next();
                break;
            default:
                return
            }
            n.preventDefault()
        }
    }
    ,
    t.prototype.cycle = function(t) {
        return t || (this.paused = !1),
        this.interval && clearInterval(this.interval),
        this.options.interval && !this.paused && (this.interval = setInterval(n.proxy(this.next, this), this.options.interval)),
        this
    }
    ,
    t.prototype.getItemIndex = function(n) {
        return this.$items = n.parent().children(".item"),
        this.$items.index(n || this.$active)
    }
    ,
    t.prototype.getItemForDirection = function(n, t) {
        var i = this.getItemIndex(t), f = "prev" == n && 0 === i || "next" == n && i == this.$items.length - 1, u, r;
        return f && !this.options.wrap ? t : (u = "prev" == n ? -1 : 1,
        r = (i + u) % this.$items.length,
        this.$items.eq(r))
    }
    ,
    t.prototype.to = function(n) {
        var i = this
          , t = this.getItemIndex(this.$active = this.$element.find(".item.active"));
        if (!(n > this.$items.length - 1) && !(n < 0))
            return this.sliding ? this.$element.one("slid.bs.carousel", function() {
                i.to(n)
            }) : t == n ? this.pause().cycle() : this.slide(n > t ? "next" : "prev", this.$items.eq(n))
    }
    ,
    t.prototype.pause = function(t) {
        return t || (this.paused = !0),
        this.$element.find(".next, .prev").length && n.support.transition && (this.$element.trigger(n.support.transition.end),
        this.cycle(!0)),
        this.interval = clearInterval(this.interval),
        this
    }
    ,
    t.prototype.next = function() {
        if (!this.sliding)
            return this.slide("next")
    }
    ,
    t.prototype.prev = function() {
        if (!this.sliding)
            return this.slide("prev")
    }
    ,
    t.prototype.slide = function(i, r) {
        var e = this.$element.find(".item.active"), u = r || this.getItemForDirection(i, e), l = this.interval, f = "next" == i ? "left" : "right", a = this, c, o, s, h;
        return u.hasClass("active") ? this.sliding = !1 : (c = u[0],
        o = n.Event("slide.bs.carousel", {
            relatedTarget: c,
            direction: f
        }),
        (this.$element.trigger(o),
        !o.isDefaultPrevented()) ? ((this.sliding = !0,
        l && this.pause(),
        this.$indicators.length) && (this.$indicators.find(".active").removeClass("active"),
        s = n(this.$indicators.children()[this.getItemIndex(u)]),
        s && s.addClass("active")),
        h = n.Event("slid.bs.carousel", {
            relatedTarget: c,
            direction: f
        }),
        n.support.transition && this.$element.hasClass("slide") ? (u.addClass(i),
        u[0].offsetWidth,
        e.addClass(f),
        u.addClass(f),
        e.one("bsTransitionEnd", function() {
            u.removeClass([i, f].join(" ")).addClass("active"),
            e.removeClass(["active", f].join(" ")),
            a.sliding = !1,
            setTimeout(function() {
                a.$element.trigger(h)
            }, 0)
        }).emulateTransitionEnd(t.TRANSITION_DURATION)) : (e.removeClass("active"),
        u.addClass("active"),
        this.sliding = !1,
        this.$element.trigger(h)),
        l && this.cycle(),
        this) : void 0)
    }
    ,
    u = n.fn.carousel,
    n.fn.carousel = r,
    n.fn.carousel.Constructor = t,
    n.fn.carousel.noConflict = function() {
        return n.fn.carousel = u,
        this
    }
    ,
    i = function(t) {
        var o, f = n(this), i = n(f.attr("data-target") || (o = f.attr("href")) && o.replace(/.*(?=#[^\s]+$)/, "")), e, u;
        i.hasClass("carousel") && (e = n.extend({}, i.data(), f.data()),
        u = f.attr("data-slide-to"),
        u && (e.interval = !1),
        r.call(i, e),
        u && i.data("bs.carousel").to(u),
        t.preventDefault())
    }
    ,
    n(document).on("click.bs.carousel.data-api", "[data-slide]", i).on("click.bs.carousel.data-api", "[data-slide-to]", i),
    n(window).on("load", function() {
        n('[data-ride="carousel"]').each(function() {
            var t = n(this);
            r.call(t, t.data())
        })
    })
}(jQuery),
+function(n) {
    "use strict";
    function u(t) {
        var i, r = t.attr("data-target") || (i = t.attr("href")) && i.replace(/.*(?=#[^\s]+$)/, "");
        return n(r)
    }
    function i(i) {
        return this.each(function() {
            var f = n(this)
              , r = f.data("bs.collapse")
              , u = n.extend({}, t.DEFAULTS, f.data(), "object" == typeof i && i);
            !r && u.toggle && /show|hide/.test(i) && (u.toggle = !1),
            r || f.data("bs.collapse", r = new t(this,u)),
            "string" == typeof i && r[i]()
        })
    }
    var t = function(i, r) {
        this.$element = n(i),
        this.options = n.extend({}, t.DEFAULTS, r),
        this.$trigger = n('[data-toggle="collapse"][href="#' + i.id + '"],[data-toggle="collapse"][data-target="#' + i.id + '"]'),
        this.transitioning = null,
        this.options.parent ? this.$parent = this.getParent() : this.addAriaAndCollapsedClass(this.$element, this.$trigger),
        this.options.toggle && this.toggle()
    }, r;
    t.VERSION = "3.3.7",
    t.TRANSITION_DURATION = 350,
    t.DEFAULTS = {
        toggle: !0
    },
    t.prototype.dimension = function() {
        var n = this.$element.hasClass("width");
        return n ? "width" : "height"
    }
    ,
    t.prototype.show = function() {
        var f, r, o, u, e, s;
        if (!this.transitioning && !this.$element.hasClass("in") && (r = this.$parent && this.$parent.children(".panel").children(".in, .collapsing"),
        !(r && r.length && (f = r.data("bs.collapse"),
        f && f.transitioning)) && (o = n.Event("show.bs.collapse"),
        this.$element.trigger(o),
        !o.isDefaultPrevented()))) {
            if (r && r.length && (i.call(r, "hide"),
            f || r.data("bs.collapse", null)),
            u = this.dimension(),
            this.$element.removeClass("collapse").addClass("collapsing")[u](0).attr("aria-expanded", !0),
            this.$trigger.removeClass("collapsed").attr("aria-expanded", !0),
            this.transitioning = 1,
            e = function() {
                this.$element.removeClass("collapsing").addClass("collapse in")[u](""),
                this.transitioning = 0,
                this.$element.trigger("shown.bs.collapse")
            }
            ,
            !n.support.transition)
                return e.call(this);
            s = n.camelCase(["scroll", u].join("-")),
            this.$element.one("bsTransitionEnd", n.proxy(e, this)).emulateTransitionEnd(t.TRANSITION_DURATION)[u](this.$element[0][s])
        }
    }
    ,
    t.prototype.hide = function() {
        var u, i, r;
        if (!this.transitioning && this.$element.hasClass("in") && (u = n.Event("hide.bs.collapse"),
        this.$element.trigger(u),
        !u.isDefaultPrevented()))
            return i = this.dimension(),
            this.$element[i](this.$element[i]())[0].offsetHeight,
            this.$element.addClass("collapsing").removeClass("collapse in").attr("aria-expanded", !1),
            this.$trigger.addClass("collapsed").attr("aria-expanded", !1),
            this.transitioning = 1,
            r = function() {
                this.transitioning = 0,
                this.$element.removeClass("collapsing").addClass("collapse").trigger("hidden.bs.collapse")
            }
            ,
            n.support.transition ? void this.$element[i](0).one("bsTransitionEnd", n.proxy(r, this)).emulateTransitionEnd(t.TRANSITION_DURATION) : r.call(this)
    }
    ,
    t.prototype.toggle = function() {
        this[this.$element.hasClass("in") ? "hide" : "show"]()
    }
    ,
    t.prototype.getParent = function() {
        return n(this.options.parent).find('[data-toggle="collapse"][data-parent="' + this.options.parent + '"]').each(n.proxy(function(t, i) {
            var r = n(i);
            this.addAriaAndCollapsedClass(u(r), r)
        }, this)).end()
    }
    ,
    t.prototype.addAriaAndCollapsedClass = function(n, t) {
        var i = n.hasClass("in");
        n.attr("aria-expanded", i),
        t.toggleClass("collapsed", !i).attr("aria-expanded", i)
    }
    ,
    r = n.fn.collapse,
    n.fn.collapse = i,
    n.fn.collapse.Constructor = t,
    n.fn.collapse.noConflict = function() {
        return n.fn.collapse = r,
        this
    }
    ,
    n(document).on("click.bs.collapse.data-api", '[data-toggle="collapse"]', function(t) {
        var r = n(this);
        r.attr("data-target") || t.preventDefault();
        var f = u(r)
          , o = f.data("bs.collapse")
          , e = o ? "toggle" : r.data();
        i.call(f, e)
    })
}(jQuery),
+function(n) {
    "use strict";
    function u(t) {
        var i = t.attr("data-target"), r;
        return i || (i = t.attr("href"),
        i = i && /#[A-Za-z]/.test(i) && i.replace(/.*(?=#[^\s]*$)/, "")),
        r = i && n(i),
        r && r.length ? r : t.parent()
    }
    function r(t) {
        t && 3 === t.which || (n(o).remove(),
        n(i).each(function() {
            var f = n(this)
              , i = u(f)
              , r = {
                relatedTarget: this
            };
            i.hasClass("open") && (t && "click" == t.type && /input|textarea/i.test(t.target.tagName) && n.contains(i[0], t.target) || (i.trigger(t = n.Event("hide.bs.dropdown", r)),
            t.isDefaultPrevented() || (f.attr("aria-expanded", "false"),
            i.removeClass("open").trigger(n.Event("hidden.bs.dropdown", r)))))
        }))
    }
    function e(i) {
        return this.each(function() {
            var u = n(this)
              , r = u.data("bs.dropdown");
            r || u.data("bs.dropdown", r = new t(this)),
            "string" == typeof i && r[i].call(u)
        })
    }
    var o = ".dropdown-backdrop", i = '[data-toggle="dropdown"]', t = function(t) {
        n(t).on("click.bs.dropdown", this.toggle)
    }, f;
    t.VERSION = "3.3.7",
    t.prototype.toggle = function(t) {
        var e = n(this), i, o, f;
        if (!e.is(".disabled, :disabled")) {
            if (i = u(e),
            o = i.hasClass("open"),
            r(),
            !o) {
                if ("ontouchstart"in document.documentElement && !i.closest(".navbar-nav").length && n(document.createElement("div")).addClass("dropdown-backdrop").insertAfter(n(this)).on("click", r),
                f = {
                    relatedTarget: this
                },
                i.trigger(t = n.Event("show.bs.dropdown", f)),
                t.isDefaultPrevented())
                    return;
                e.trigger("focus").attr("aria-expanded", "true"),
                i.toggleClass("open").trigger(n.Event("shown.bs.dropdown", f))
            }
            return !1
        }
    }
    ,
    t.prototype.keydown = function(t) {
        var o, e, s, h, f, r;
        if (/(38|40|27|32)/.test(t.which) && !/input|textarea/i.test(t.target.tagName) && (o = n(this),
        t.preventDefault(),
        t.stopPropagation(),
        !o.is(".disabled, :disabled"))) {
            if (e = u(o),
            s = e.hasClass("open"),
            !s && 27 != t.which || s && 27 == t.which)
                return 27 == t.which && e.find(i).trigger("focus"),
                o.trigger("click");
            h = " li:not(.disabled):visible a",
            f = e.find(".dropdown-menu" + h),
            f.length && (r = f.index(t.target),
            38 == t.which && r > 0 && r--,
            40 == t.which && r < f.length - 1 && r++,
            ~r || (r = 0),
            f.eq(r).trigger("focus"))
        }
    }
    ,
    f = n.fn.dropdown,
    n.fn.dropdown = e,
    n.fn.dropdown.Constructor = t,
    n.fn.dropdown.noConflict = function() {
        return n.fn.dropdown = f,
        this
    }
    ,
    n(document).on("click.bs.dropdown.data-api", r).on("click.bs.dropdown.data-api", ".dropdown form", function(n) {
        n.stopPropagation()
    }).on("click.bs.dropdown.data-api", i, t.prototype.toggle).on("keydown.bs.dropdown.data-api", i, t.prototype.keydown).on("keydown.bs.dropdown.data-api", ".dropdown-menu", t.prototype.keydown)
}(jQuery),
+function(n) {
    "use strict";
    function r(i, r) {
        return this.each(function() {
            var f = n(this)
              , u = f.data("bs.modal")
              , e = n.extend({}, t.DEFAULTS, f.data(), "object" == typeof i && i);
            u || f.data("bs.modal", u = new t(this,e)),
            "string" == typeof i ? u[i](r) : e.show && u.show(r)
        })
    }
    var t = function(t, i) {
        this.options = i,
        this.$body = n(document.body),
        this.$element = n(t),
        this.$dialog = this.$element.find(".modal-dialog"),
        this.$backdrop = null,
        this.isShown = null,
        this.originalBodyPad = null,
        this.scrollbarWidth = 0,
        this.ignoreBackdropClick = !1,
        this.options.remote && this.$element.find(".modal-content").load(this.options.remote, n.proxy(function() {
            this.$element.trigger("loaded.bs.modal")
        }, this))
    }, i;
    t.VERSION = "3.3.7",
    t.TRANSITION_DURATION = 300,
    t.BACKDROP_TRANSITION_DURATION = 150,
    t.DEFAULTS = {
        backdrop: !0,
        keyboard: !0,
        show: !0
    },
    t.prototype.toggle = function(n) {
        return this.isShown ? this.hide() : this.show(n)
    }
    ,
    t.prototype.show = function(i) {
        var r = this
          , u = n.Event("show.bs.modal", {
            relatedTarget: i
        });
        this.$element.trigger(u),
        this.isShown || u.isDefaultPrevented() || (this.isShown = !0,
        this.checkScrollbar(),
        this.setScrollbar(),
        this.$body.addClass("modal-open"),
        this.escape(),
        this.resize(),
        this.$element.on("click.dismiss.bs.modal", '[data-dismiss="modal"]', n.proxy(this.hide, this)),
        this.$dialog.on("mousedown.dismiss.bs.modal", function() {
            r.$element.one("mouseup.dismiss.bs.modal", function(t) {
                n(t.target).is(r.$element) && (r.ignoreBackdropClick = !0)
            })
        }),
        this.backdrop(function() {
            var f = n.support.transition && r.$element.hasClass("fade"), u;
            r.$element.parent().length || r.$element.appendTo(r.$body),
            r.$element.show().scrollTop(0),
            r.adjustDialog(),
            f && r.$element[0].offsetWidth,
            r.$element.addClass("in"),
            r.enforceFocus(),
            u = n.Event("shown.bs.modal", {
                relatedTarget: i
            }),
            f ? r.$dialog.one("bsTransitionEnd", function() {
                r.$element.trigger("focus").trigger(u)
            }).emulateTransitionEnd(t.TRANSITION_DURATION) : r.$element.trigger("focus").trigger(u)
        }))
    }
    ,
    t.prototype.hide = function(i) {
        i && i.preventDefault(),
        i = n.Event("hide.bs.modal"),
        this.$element.trigger(i),
        this.isShown && !i.isDefaultPrevented() && (this.isShown = !1,
        this.escape(),
        this.resize(),
        n(document).off("focusin.bs.modal"),
        this.$element.removeClass("in").off("click.dismiss.bs.modal").off("mouseup.dismiss.bs.modal"),
        this.$dialog.off("mousedown.dismiss.bs.modal"),
        n.support.transition && this.$element.hasClass("fade") ? this.$element.one("bsTransitionEnd", n.proxy(this.hideModal, this)).emulateTransitionEnd(t.TRANSITION_DURATION) : this.hideModal())
    }
    ,
    t.prototype.enforceFocus = function() {
        n(document).off("focusin.bs.modal").on("focusin.bs.modal", n.proxy(function(n) {
            document === n.target || this.$element[0] === n.target || this.$element.has(n.target).length || this.$element.trigger("focus")
        }, this))
    }
    ,
    t.prototype.escape = function() {
        this.isShown && this.options.keyboard ? this.$element.on("keydown.dismiss.bs.modal", n.proxy(function(n) {
            27 == n.which && this.hide()
        }, this)) : this.isShown || this.$element.off("keydown.dismiss.bs.modal")
    }
    ,
    t.prototype.resize = function() {
        this.isShown ? n(window).on("resize.bs.modal", n.proxy(this.handleUpdate, this)) : n(window).off("resize.bs.modal")
    }
    ,
    t.prototype.hideModal = function() {
        var n = this;
        this.$element.hide(),
        this.backdrop(function() {
            n.$body.removeClass("modal-open"),
            n.resetAdjustments(),
            n.resetScrollbar(),
            n.$element.trigger("hidden.bs.modal")
        })
    }
    ,
    t.prototype.removeBackdrop = function() {
        this.$backdrop && this.$backdrop.remove(),
        this.$backdrop = null
    }
    ,
    t.prototype.backdrop = function(i) {
        var e = this, f = this.$element.hasClass("fade") ? "fade" : "", r, u;
        if (this.isShown && this.options.backdrop) {
            if (r = n.support.transition && f,
            this.$backdrop = n(document.createElement("div")).addClass("modal-backdrop " + f).appendTo(this.$body),
            this.$element.on("click.dismiss.bs.modal", n.proxy(function(n) {
                return this.ignoreBackdropClick ? void (this.ignoreBackdropClick = !1) : void (n.target === n.currentTarget && ("static" == this.options.backdrop ? this.$element[0].focus() : this.hide()))
            }, this)),
            r && this.$backdrop[0].offsetWidth,
            this.$backdrop.addClass("in"),
            !i)
                return;
            r ? this.$backdrop.one("bsTransitionEnd", i).emulateTransitionEnd(t.BACKDROP_TRANSITION_DURATION) : i()
        } else
            !this.isShown && this.$backdrop ? (this.$backdrop.removeClass("in"),
            u = function() {
                e.removeBackdrop(),
                i && i()
            }
            ,
            n.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one("bsTransitionEnd", u).emulateTransitionEnd(t.BACKDROP_TRANSITION_DURATION) : u()) : i && i()
    }
    ,
    t.prototype.handleUpdate = function() {
        this.adjustDialog()
    }
    ,
    t.prototype.adjustDialog = function() {
        var n = this.$element[0].scrollHeight > document.documentElement.clientHeight;
        this.$element.css({
            paddingLeft: !this.bodyIsOverflowing && n ? this.scrollbarWidth : "",
            paddingRight: this.bodyIsOverflowing && !n ? this.scrollbarWidth : ""
        })
    }
    ,
    t.prototype.resetAdjustments = function() {
        this.$element.css({
            paddingLeft: "",
            paddingRight: ""
        })
    }
    ,
    t.prototype.checkScrollbar = function() {
        var t = window.innerWidth, n;
        t || (n = document.documentElement.getBoundingClientRect(),
        t = n.right - Math.abs(n.left)),
        this.bodyIsOverflowing = document.body.clientWidth < t,
        this.scrollbarWidth = this.measureScrollbar()
    }
    ,
    t.prototype.setScrollbar = function() {
        var n = parseInt(this.$body.css("padding-right") || 0, 10);
        this.originalBodyPad = document.body.style.paddingRight || "",
        this.bodyIsOverflowing && this.$body.css("padding-right", n + this.scrollbarWidth)
    }
    ,
    t.prototype.resetScrollbar = function() {
        this.$body.css("padding-right", this.originalBodyPad)
    }
    ,
    t.prototype.measureScrollbar = function() {
        var n = document.createElement("div"), t;
        return n.className = "modal-scrollbar-measure",
        this.$body.append(n),
        t = n.offsetWidth - n.clientWidth,
        this.$body[0].removeChild(n),
        t
    }
    ,
    i = n.fn.modal,
    n.fn.modal = r,
    n.fn.modal.Constructor = t,
    n.fn.modal.noConflict = function() {
        return n.fn.modal = i,
        this
    }
    ,
    n(document).on("click.bs.modal.data-api", '[data-toggle="modal"]', function(t) {
        var i = n(this)
          , f = i.attr("href")
          , u = n(i.attr("data-target") || f && f.replace(/.*(?=#[^\s]+$)/, ""))
          , e = u.data("bs.modal") ? "toggle" : n.extend({
            remote: !/#/.test(f) && f
        }, u.data(), i.data());
        i.is("a") && t.preventDefault(),
        u.one("show.bs.modal", function(n) {
            n.isDefaultPrevented() || u.one("hidden.bs.modal", function() {
                i.is(":visible") && i.trigger("focus")
            })
        }),
        r.call(u, e, this)
    })
}(jQuery),
+function(n) {
    "use strict";
    function r(i) {
        return this.each(function() {
            var u = n(this)
              , r = u.data("bs.tooltip")
              , f = "object" == typeof i && i;
            !r && /destroy|hide/.test(i) || (r || u.data("bs.tooltip", r = new t(this,f)),
            "string" == typeof i && r[i]())
        })
    }
    var t = function(n, t) {
        this.type = null,
        this.options = null,
        this.enabled = null,
        this.timeout = null,
        this.hoverState = null,
        this.$element = null,
        this.inState = null,
        this.init("tooltip", n, t)
    }, i;
    t.VERSION = "3.3.7",
    t.TRANSITION_DURATION = 150,
    t.DEFAULTS = {
        animation: !0,
        placement: "top",
        selector: !1,
        template: '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',
        trigger: "hover focus",
        title: "",
        delay: 0,
        html: !1,
        container: !1,
        viewport: {
            selector: "body",
            padding: 0
        }
    },
    t.prototype.init = function(t, i, r) {
        var e, f, u, s, o;
        if (this.enabled = !0,
        this.type = t,
        this.$element = n(i),
        this.options = this.getOptions(r),
        this.$viewport = this.options.viewport && n(n.isFunction(this.options.viewport) ? this.options.viewport.call(this, this.$element) : this.options.viewport.selector || this.options.viewport),
        this.inState = {
            click: !1,
            hover: !1,
            focus: !1
        },
        this.$element[0]instanceof document.constructor && !this.options.selector)
            throw new Error("`selector` option must be specified when initializing " + this.type + " on the window.document object!");
        for (e = this.options.trigger.split(" "),
        f = e.length; f--; )
            if (u = e[f],
            "click" == u)
                this.$element.on("click." + this.type, this.options.selector, n.proxy(this.toggle, this));
            else
                "manual" != u && (s = "hover" == u ? "mouseenter" : "focusin",
                o = "hover" == u ? "mouseleave" : "focusout",
                this.$element.on(s + "." + this.type, this.options.selector, n.proxy(this.enter, this)),
                this.$element.on(o + "." + this.type, this.options.selector, n.proxy(this.leave, this)));
        this.options.selector ? this._options = n.extend({}, this.options, {
            trigger: "manual",
            selector: ""
        }) : this.fixTitle()
    }
    ,
    t.prototype.getDefaults = function() {
        return t.DEFAULTS
    }
    ,
    t.prototype.getOptions = function(t) {
        return t = n.extend({}, this.getDefaults(), this.$element.data(), t),
        t.delay && "number" == typeof t.delay && (t.delay = {
            show: t.delay,
            hide: t.delay
        }),
        t
    }
    ,
    t.prototype.getDelegateOptions = function() {
        var t = {}
          , i = this.getDefaults();
        return this._options && n.each(this._options, function(n, r) {
            i[n] != r && (t[n] = r)
        }),
        t
    }
    ,
    t.prototype.enter = function(t) {
        var i = t instanceof this.constructor ? t : n(t.currentTarget).data("bs." + this.type);
        return i || (i = new this.constructor(t.currentTarget,this.getDelegateOptions()),
        n(t.currentTarget).data("bs." + this.type, i)),
        t instanceof n.Event && (i.inState["focusin" == t.type ? "focus" : "hover"] = !0),
        i.tip().hasClass("in") || "in" == i.hoverState ? void (i.hoverState = "in") : (clearTimeout(i.timeout),
        i.hoverState = "in",
        i.options.delay && i.options.delay.show ? void (i.timeout = setTimeout(function() {
            "in" == i.hoverState && i.show()
        }, i.options.delay.show)) : i.show())
    }
    ,
    t.prototype.isInStateTrue = function() {
        for (var n in this.inState)
            if (this.inState[n])
                return !0;
        return !1
    }
    ,
    t.prototype.leave = function(t) {
        var i = t instanceof this.constructor ? t : n(t.currentTarget).data("bs." + this.type);
        if (i || (i = new this.constructor(t.currentTarget,this.getDelegateOptions()),
        n(t.currentTarget).data("bs." + this.type, i)),
        t instanceof n.Event && (i.inState["focusout" == t.type ? "focus" : "hover"] = !1),
        !i.isInStateTrue())
            return clearTimeout(i.timeout),
            i.hoverState = "out",
            i.options.delay && i.options.delay.hide ? void (i.timeout = setTimeout(function() {
                "out" == i.hoverState && i.hide()
            }, i.options.delay.hide)) : i.hide()
    }
    ,
    t.prototype.show = function() {
        var c = n.Event("show.bs." + this.type), y, a, e, l, h;
        if (this.hasContent() && this.enabled) {
            if (this.$element.trigger(c),
            y = n.contains(this.$element[0].ownerDocument.documentElement, this.$element[0]),
            c.isDefaultPrevented() || !y)
                return;
            var u = this
              , r = this.tip()
              , v = this.getUID(this.type);
            this.setContent(),
            r.attr("id", v),
            this.$element.attr("aria-describedby", v),
            this.options.animation && r.addClass("fade");
            var i = "function" == typeof this.options.placement ? this.options.placement.call(this, r[0], this.$element[0]) : this.options.placement
              , w = /\s?auto?\s?/i
              , p = w.test(i);
            p && (i = i.replace(w, "") || "top"),
            r.detach().css({
                top: 0,
                left: 0,
                display: "block"
            }).addClass(i).data("bs." + this.type, this),
            this.options.container ? r.appendTo(this.options.container) : r.insertAfter(this.$element),
            this.$element.trigger("inserted.bs." + this.type);
            var f = this.getPosition()
              , o = r[0].offsetWidth
              , s = r[0].offsetHeight;
            p && (a = i,
            e = this.getPosition(this.$viewport),
            i = "bottom" == i && f.bottom + s > e.bottom ? "top" : "top" == i && f.top - s < e.top ? "bottom" : "right" == i && f.right + o > e.width ? "left" : "left" == i && f.left - o < e.left ? "right" : i,
            r.removeClass(a).addClass(i)),
            l = this.getCalculatedOffset(i, f, o, s),
            this.applyPlacement(l, i),
            h = function() {
                var n = u.hoverState;
                u.$element.trigger("shown.bs." + u.type),
                u.hoverState = null,
                "out" == n && u.leave(u)
            }
            ,
            n.support.transition && this.$tip.hasClass("fade") ? r.one("bsTransitionEnd", h).emulateTransitionEnd(t.TRANSITION_DURATION) : h()
        }
    }
    ,
    t.prototype.applyPlacement = function(t, i) {
        var r = this.tip(), v = r[0].offsetWidth, s = r[0].offsetHeight, h = parseInt(r.css("margin-top"), 10), c = parseInt(r.css("margin-left"), 10), e, f, u;
        isNaN(h) && (h = 0),
        isNaN(c) && (c = 0),
        t.top += h,
        t.left += c,
        n.offset.setOffset(r[0], n.extend({
            using: function(n) {
                r.css({
                    top: Math.round(n.top),
                    left: Math.round(n.left)
                })
            }
        }, t), 0),
        r.addClass("in"),
        e = r[0].offsetWidth,
        f = r[0].offsetHeight,
        "top" == i && f != s && (t.top = t.top + s - f),
        u = this.getViewportAdjustedDelta(i, t, e, f),
        u.left ? t.left += u.left : t.top += u.top;
        var o = /top|bottom/.test(i)
          , a = o ? 2 * u.left - v + e : 2 * u.top - s + f
          , l = o ? "offsetWidth" : "offsetHeight";
        r.offset(t),
        this.replaceArrow(a, r[0][l], o)
    }
    ,
    t.prototype.replaceArrow = function(n, t, i) {
        this.arrow().css(i ? "left" : "top", 50 * (1 - n / t) + "%").css(i ? "top" : "left", "")
    }
    ,
    t.prototype.setContent = function() {
        var n = this.tip()
          , t = this.getTitle();
        n.find(".tooltip-inner")[this.options.html ? "html" : "text"](t),
        n.removeClass("fade in top bottom left right")
    }
    ,
    t.prototype.hide = function(i) {
        function e() {
            "in" != u.hoverState && r.detach(),
            u.$element && u.$element.removeAttr("aria-describedby").trigger("hidden.bs." + u.type),
            i && i()
        }
        var u = this
          , r = n(this.$tip)
          , f = n.Event("hide.bs." + this.type);
        if (this.$element.trigger(f),
        !f.isDefaultPrevented())
            return r.removeClass("in"),
            n.support.transition && r.hasClass("fade") ? r.one("bsTransitionEnd", e).emulateTransitionEnd(t.TRANSITION_DURATION) : e(),
            this.hoverState = null,
            this
    }
    ,
    t.prototype.fixTitle = function() {
        var n = this.$element;
        (n.attr("title") || "string" != typeof n.attr("data-original-title")) && n.attr("data-original-title", n.attr("title") || "").attr("title", "")
    }
    ,
    t.prototype.hasContent = function() {
        return this.getTitle()
    }
    ,
    t.prototype.getPosition = function(t) {
        t = t || this.$element;
        var r = t[0]
          , u = "BODY" == r.tagName
          , i = r.getBoundingClientRect();
        null == i.width && (i = n.extend({}, i, {
            width: i.right - i.left,
            height: i.bottom - i.top
        }));
        var o = window.SVGElement && r instanceof window.SVGElement
          , s = u ? {
            top: 0,
            left: 0
        } : o ? null : t.offset()
          , f = {
            scroll: u ? document.documentElement.scrollTop || document.body.scrollTop : t.scrollTop()
        }
          , e = u ? {
            width: n(window).width(),
            height: n(window).height()
        } : null;
        return n.extend({}, i, f, e, s)
    }
    ,
    t.prototype.getCalculatedOffset = function(n, t, i, r) {
        return "bottom" == n ? {
            top: t.top + t.height,
            left: t.left + t.width / 2 - i / 2
        } : "top" == n ? {
            top: t.top - r,
            left: t.left + t.width / 2 - i / 2
        } : "left" == n ? {
            top: t.top + t.height / 2 - r / 2,
            left: t.left - i
        } : {
            top: t.top + t.height / 2 - r / 2,
            left: t.left + t.width
        }
    }
    ,
    t.prototype.getViewportAdjustedDelta = function(n, t, i, r) {
        var f = {
            top: 0,
            left: 0
        }, e, u, h, c, o, s;
        return this.$viewport ? (e = this.options.viewport && this.options.viewport.padding || 0,
        u = this.getPosition(this.$viewport),
        /right|left/.test(n) ? (h = t.top - e - u.scroll,
        c = t.top + e - u.scroll + r,
        h < u.top ? f.top = u.top - h : c > u.top + u.height && (f.top = u.top + u.height - c)) : (o = t.left - e,
        s = t.left + e + i,
        o < u.left ? f.left = u.left - o : s > u.right && (f.left = u.left + u.width - s)),
        f) : f
    }
    ,
    t.prototype.getTitle = function() {
        var i, t = this.$element, n = this.options;
        return i = t.attr("data-original-title") || ("function" == typeof n.title ? n.title.call(t[0]) : n.title)
    }
    ,
    t.prototype.getUID = function(n) {
        do
            n += ~~(1e6 * Math.random());
        while (document.getElementById(n));
        return n
    }
    ,
    t.prototype.tip = function() {
        if (!this.$tip && (this.$tip = n(this.options.template),
        1 != this.$tip.length))
            throw new Error(this.type + " `template` option must consist of exactly 1 top-level element!");
        return this.$tip
    }
    ,
    t.prototype.arrow = function() {
        return this.$arrow = this.$arrow || this.tip().find(".tooltip-arrow")
    }
    ,
    t.prototype.enable = function() {
        this.enabled = !0
    }
    ,
    t.prototype.disable = function() {
        this.enabled = !1
    }
    ,
    t.prototype.toggleEnabled = function() {
        this.enabled = !this.enabled
    }
    ,
    t.prototype.toggle = function(t) {
        var i = this;
        t && (i = n(t.currentTarget).data("bs." + this.type),
        i || (i = new this.constructor(t.currentTarget,this.getDelegateOptions()),
        n(t.currentTarget).data("bs." + this.type, i))),
        t ? (i.inState.click = !i.inState.click,
        i.isInStateTrue() ? i.enter(i) : i.leave(i)) : i.tip().hasClass("in") ? i.leave(i) : i.enter(i)
    }
    ,
    t.prototype.destroy = function() {
        var n = this;
        clearTimeout(this.timeout),
        this.hide(function() {
            n.$element.off("." + n.type).removeData("bs." + n.type),
            n.$tip && n.$tip.detach(),
            n.$tip = null,
            n.$arrow = null,
            n.$viewport = null,
            n.$element = null
        })
    }
    ,
    i = n.fn.tooltip,
    n.fn.tooltip = r,
    n.fn.tooltip.Constructor = t,
    n.fn.tooltip.noConflict = function() {
        return n.fn.tooltip = i,
        this
    }
}(jQuery),
+function(n) {
    "use strict";
    function r(i) {
        return this.each(function() {
            var u = n(this)
              , r = u.data("bs.popover")
              , f = "object" == typeof i && i;
            !r && /destroy|hide/.test(i) || (r || u.data("bs.popover", r = new t(this,f)),
            "string" == typeof i && r[i]())
        })
    }
    var t = function(n, t) {
        this.init("popover", n, t)
    }, i;
    if (!n.fn.tooltip)
        throw new Error("Popover requires tooltip.js");
    t.VERSION = "3.3.7",
    t.DEFAULTS = n.extend({}, n.fn.tooltip.Constructor.DEFAULTS, {
        placement: "right",
        trigger: "click",
        content: "",
        template: '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
    }),
    t.prototype = n.extend({}, n.fn.tooltip.Constructor.prototype),
    t.prototype.constructor = t,
    t.prototype.getDefaults = function() {
        return t.DEFAULTS
    }
    ,
    t.prototype.setContent = function() {
        var n = this.tip()
          , i = this.getTitle()
          , t = this.getContent();
        n.find(".popover-title")[this.options.html ? "html" : "text"](i),
        n.find(".popover-content").children().detach().end()[this.options.html ? "string" == typeof t ? "html" : "append" : "text"](t),
        n.removeClass("fade top bottom left right in"),
        n.find(".popover-title").html() || n.find(".popover-title").hide()
    }
    ,
    t.prototype.hasContent = function() {
        return this.getTitle() || this.getContent()
    }
    ,
    t.prototype.getContent = function() {
        var t = this.$element
          , n = this.options;
        return t.attr("data-content") || ("function" == typeof n.content ? n.content.call(t[0]) : n.content)
    }
    ,
    t.prototype.arrow = function() {
        return this.$arrow = this.$arrow || this.tip().find(".arrow")
    }
    ,
    i = n.fn.popover,
    n.fn.popover = r,
    n.fn.popover.Constructor = t,
    n.fn.popover.noConflict = function() {
        return n.fn.popover = i,
        this
    }
}(jQuery),
+function(n) {
    "use strict";
    function t(i, r) {
        this.$body = n(document.body),
        this.$scrollElement = n(n(i).is(document.body) ? window : i),
        this.options = n.extend({}, t.DEFAULTS, r),
        this.selector = (this.options.target || "") + " .nav li > a",
        this.offsets = [],
        this.targets = [],
        this.activeTarget = null,
        this.scrollHeight = 0,
        this.$scrollElement.on("scroll.bs.scrollspy", n.proxy(this.process, this)),
        this.refresh(),
        this.process()
    }
    function i(i) {
        return this.each(function() {
            var u = n(this)
              , r = u.data("bs.scrollspy")
              , f = "object" == typeof i && i;
            r || u.data("bs.scrollspy", r = new t(this,f)),
            "string" == typeof i && r[i]()
        })
    }
    t.VERSION = "3.3.7",
    t.DEFAULTS = {
        offset: 10
    },
    t.prototype.getScrollHeight = function() {
        return this.$scrollElement[0].scrollHeight || Math.max(this.$body[0].scrollHeight, document.documentElement.scrollHeight)
    }
    ,
    t.prototype.refresh = function() {
        var r = this
          , i = "offset"
          , t = 0;
        this.offsets = [],
        this.targets = [],
        this.scrollHeight = this.getScrollHeight(),
        n.isWindow(this.$scrollElement[0]) || (i = "position",
        t = this.$scrollElement.scrollTop()),
        this.$body.find(this.selector).map(function() {
            var f = n(this)
              , u = f.data("target") || f.attr("href")
              , r = /^#./.test(u) && n(u);
            return r && r.length && r.is(":visible") && [[r[i]().top + t, u]] || null
        }).sort(function(n, t) {
            return n[0] - t[0]
        }).each(function() {
            r.offsets.push(this[0]),
            r.targets.push(this[1])
        })
    }
    ,
    t.prototype.process = function() {
        var n, i = this.$scrollElement.scrollTop() + this.options.offset, f = this.getScrollHeight(), e = this.options.offset + f - this.$scrollElement.height(), t = this.offsets, r = this.targets, u = this.activeTarget;
        if (this.scrollHeight != f && this.refresh(),
        i >= e)
            return u != (n = r[r.length - 1]) && this.activate(n);
        if (u && i < t[0])
            return this.activeTarget = null,
            this.clear();
        for (n = t.length; n--; )
            u != r[n] && i >= t[n] && (void 0 === t[n + 1] || i < t[n + 1]) && this.activate(r[n])
    }
    ,
    t.prototype.activate = function(t) {
        this.activeTarget = t,
        this.clear();
        var r = this.selector + '[data-target="' + t + '"],' + this.selector + '[href="' + t + '"]'
          , i = n(r).parents("li").addClass("active");
        i.parent(".dropdown-menu").length && (i = i.closest("li.dropdown").addClass("active")),
        i.trigger("activate.bs.scrollspy")
    }
    ,
    t.prototype.clear = function() {
        n(this.selector).parentsUntil(this.options.target, ".active").removeClass("active")
    }
    ;
    var r = n.fn.scrollspy;
    n.fn.scrollspy = i,
    n.fn.scrollspy.Constructor = t,
    n.fn.scrollspy.noConflict = function() {
        return n.fn.scrollspy = r,
        this
    }
    ,
    n(window).on("load.bs.scrollspy.data-api", function() {
        n('[data-spy="scroll"]').each(function() {
            var t = n(this);
            i.call(t, t.data())
        })
    })
}(jQuery),
+function(n) {
    "use strict";
    function r(i) {
        return this.each(function() {
            var u = n(this)
              , r = u.data("bs.tab");
            r || u.data("bs.tab", r = new t(this)),
            "string" == typeof i && r[i]()
        })
    }
    var t = function(t) {
        this.element = n(t)
    }, u, i;
    t.VERSION = "3.3.7",
    t.TRANSITION_DURATION = 150,
    t.prototype.show = function() {
        var t = this.element, f = t.closest("ul:not(.dropdown-menu)"), i = t.data("target"), u;
        if (i || (i = t.attr("href"),
        i = i && i.replace(/.*(?=#[^\s]*$)/, "")),
        !t.parent("li").hasClass("active")) {
            var r = f.find(".active:last a")
              , o = n.Event("hide.bs.tab", {
                relatedTarget: t[0]
            })
              , e = n.Event("show.bs.tab", {
                relatedTarget: r[0]
            });
            (r.trigger(o),
            t.trigger(e),
            e.isDefaultPrevented() || o.isDefaultPrevented()) || (u = n(i),
            this.activate(t.closest("li"), f),
            this.activate(u, u.parent(), function() {
                r.trigger({
                    type: "hidden.bs.tab",
                    relatedTarget: t[0]
                }),
                t.trigger({
                    type: "shown.bs.tab",
                    relatedTarget: r[0]
                })
            }))
        }
    }
    ,
    t.prototype.activate = function(i, r, u) {
        function o() {
            f.removeClass("active").find("> .dropdown-menu > .active").removeClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded", !1),
            i.addClass("active").find('[data-toggle="tab"]').attr("aria-expanded", !0),
            e ? (i[0].offsetWidth,
            i.addClass("in")) : i.removeClass("fade"),
            i.parent(".dropdown-menu").length && i.closest("li.dropdown").addClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded", !0),
            u && u()
        }
        var f = r.find("> .active")
          , e = u && n.support.transition && (f.length && f.hasClass("fade") || !!r.find("> .fade").length);
        f.length && e ? f.one("bsTransitionEnd", o).emulateTransitionEnd(t.TRANSITION_DURATION) : o(),
        f.removeClass("in")
    }
    ,
    u = n.fn.tab,
    n.fn.tab = r,
    n.fn.tab.Constructor = t,
    n.fn.tab.noConflict = function() {
        return n.fn.tab = u,
        this
    }
    ,
    i = function(t) {
        t.preventDefault(),
        r.call(n(this), "show")
    }
    ;
    n(document).on("click.bs.tab.data-api", '[data-toggle="tab"]', i).on("click.bs.tab.data-api", '[data-toggle="pill"]', i)
}(jQuery),
+function(n) {
    "use strict";
    function r(i) {
        return this.each(function() {
            var u = n(this)
              , r = u.data("bs.affix")
              , f = "object" == typeof i && i;
            r || u.data("bs.affix", r = new t(this,f)),
            "string" == typeof i && r[i]()
        })
    }
    var t = function(i, r) {
        this.options = n.extend({}, t.DEFAULTS, r),
        this.$target = n(this.options.target).on("scroll.bs.affix.data-api", n.proxy(this.checkPosition, this)).on("click.bs.affix.data-api", n.proxy(this.checkPositionWithEventLoop, this)),
        this.$element = n(i),
        this.affixed = null,
        this.unpin = null,
        this.pinnedOffset = null,
        this.checkPosition()
    }, i;
    t.VERSION = "3.3.7",
    t.RESET = "affix affix-top affix-bottom",
    t.DEFAULTS = {
        offset: 0,
        target: window
    },
    t.prototype.getState = function(n, t, i, r) {
        var u = this.$target.scrollTop()
          , o = this.$element.offset()
          , e = this.$target.height();
        if (null != i && "top" == this.affixed)
            return u < i && "top";
        if ("bottom" == this.affixed)
            return null != i ? !(u + this.unpin <= o.top) && "bottom" : !(u + e <= n - r) && "bottom";
        var f = null == this.affixed
          , h = f ? u : o.top
          , s = f ? e : t;
        return null != i && u <= i ? "top" : null != r && h + s >= n - r && "bottom"
    }
    ,
    t.prototype.getPinnedOffset = function() {
        if (this.pinnedOffset)
            return this.pinnedOffset;
        this.$element.removeClass(t.RESET).addClass("affix");
        var i = this.$target.scrollTop()
          , n = this.$element.offset();
        return this.pinnedOffset = n.top - i
    }
    ,
    t.prototype.checkPositionWithEventLoop = function() {
        setTimeout(n.proxy(this.checkPosition, this), 1)
    }
    ,
    t.prototype.checkPosition = function() {
        var i, e, o;
        if (this.$element.is(":visible")) {
            var s = this.$element.height()
              , r = this.options.offset
              , f = r.top
              , u = r.bottom
              , h = Math.max(n(document).height(), n(document.body).height());
            if ("object" != typeof r && (u = f = r),
            "function" == typeof f && (f = r.top(this.$element)),
            "function" == typeof u && (u = r.bottom(this.$element)),
            i = this.getState(h, s, f, u),
            this.affixed != i) {
                if (null != this.unpin && this.$element.css("top", ""),
                e = "affix" + (i ? "-" + i : ""),
                o = n.Event(e + ".bs.affix"),
                this.$element.trigger(o),
                o.isDefaultPrevented())
                    return;
                this.affixed = i,
                this.unpin = "bottom" == i ? this.getPinnedOffset() : null,
                this.$element.removeClass(t.RESET).addClass(e).trigger(e.replace("affix", "affixed") + ".bs.affix")
            }
            "bottom" == i && this.$element.offset({
                top: h - s - u
            })
        }
    }
    ,
    i = n.fn.affix,
    n.fn.affix = r,
    n.fn.affix.Constructor = t,
    n.fn.affix.noConflict = function() {
        return n.fn.affix = i,
        this
    }
    ,
    n(window).on("load", function() {
        n('[data-spy="affix"]').each(function() {
            var i = n(this)
              , t = i.data();
            t.offset = t.offset || {},
            null != t.offsetBottom && (t.offset.bottom = t.offsetBottom),
            null != t.offsetTop && (t.offset.top = t.offsetTop),
            r.call(i, t)
        })
    })
}(jQuery)
