namespace GiaoHangTietKiem.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("UserKH")]
    public partial class UserKH
    {
        private string sDT1;
        private string matKhau1;
        private string email1;
        private string userName1;

        public UserKH(string sDT1, string matKhau1, string email1, string makh, string userName1)
        {
            this.sDT1 = sDT1;
            this.matKhau1 = matKhau1;
            this.email1 = email1;
            MaKH = makh;
            this.userName1 = userName1;
        }
        public UserKH() { }
        [Key]
        [StringLength(10)]
        public string SDT { get; set; }

        [Required]
        [StringLength(20)]
        public string MatKhau { get; set; }

        [StringLength(40)]
        public string Email { get; set; }

        [StringLength(10)]
        public string MaKH { get; set; }

        [Required]
        [StringLength(30)]
        public string UserName { get; set; }

        public virtual KhachHang KhachHang { get; set; }
    }
}
