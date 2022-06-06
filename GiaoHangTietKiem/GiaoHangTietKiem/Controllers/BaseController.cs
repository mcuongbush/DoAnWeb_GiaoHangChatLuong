using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace GiaoHangTietKiem.Controllers
{
    public class BaseController : Controller
    {
        // GET: Base
        protected override void OnActionExecuting(ActionExecutingContext filterContext)
        {
            //var sess = (UserLogin)Session[Common.Common.USER_SESSION];
            //if (sess == null)
            //{
            //    filterContext.Result = new RedirectToRouteResult(new System.Web.Routing.RouteValueDictionary(new { Controller = "Login", action = "Login", Area = "Admin" }));
            //}
            //base.OnActionExecuting(filterContext);
        }
    }
}