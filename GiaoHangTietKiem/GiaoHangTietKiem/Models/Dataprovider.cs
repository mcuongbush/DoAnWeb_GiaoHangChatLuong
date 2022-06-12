
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Models
{
    public class Dataprovider
    {
        private static Dataprovider _Instance;

        internal static Dataprovider Instance { get { if (_Instance == null) _Instance = new Dataprovider(); return _Instance; } set => _Instance = value; }
        public GiaohangchatluongContext DB;
        public Dataprovider()
        {
            DB = new GiaohangchatluongContext();
        }
    }
}