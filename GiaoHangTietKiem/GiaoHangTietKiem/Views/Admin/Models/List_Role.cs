using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Views.Admin.Models
{
    public class List_Role
    {
        public string Name { get; set; }
        public IEnumerable<Role_Temp> RoleList { get; set; }
    }
}