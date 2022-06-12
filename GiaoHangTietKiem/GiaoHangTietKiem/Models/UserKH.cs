namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("UserKH")]
    public partial class UserKH
    {
        public UserKH() { }
        public UserKH(string sDT, string matKhau, string email, string maKH, string userName)
        {
            SDT = sDT;
            MatKhau = matKhau;
            Email = email;
            MaKH = maKH;
            UserName = userName;
        }

        [Key]
        [StringLength(10)]
        public string SDT { get; set; }

        [Required]
        [StringLength(20)]
        public string MatKhau { get; set; }

        [Required]
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
