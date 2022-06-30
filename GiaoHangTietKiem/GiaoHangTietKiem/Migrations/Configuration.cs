namespace GiaoHangTietKiem.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<GiaoHangTietKiem.Models.GiaoHangChatLuongContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }


    }
}
