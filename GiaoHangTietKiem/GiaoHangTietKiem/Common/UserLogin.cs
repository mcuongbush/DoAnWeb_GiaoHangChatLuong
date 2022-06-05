using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem
{
    [Serializable]
    public class UserLogin
    {
        public String UserID { set; get; }
        public string UserName { set; get; }
    }
}