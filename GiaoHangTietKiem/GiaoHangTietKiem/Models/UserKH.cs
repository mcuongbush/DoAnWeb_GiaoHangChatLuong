namespace GiaoHangTietKiem.App_Start
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("UserKH")]
    public partial class UserKH
    {
        [Key]
        [StringLength(10)]
        public string SDT { get; set; }

        [Required]
        [StringLength(20)]
        public string MatKhau { get; set; }

        [Required]
        [StringLength(20)]
        public string Email { get; set; }

        [StringLength(10)]
        public string MaKH { get; set; }

        public virtual KhachHang KhachHang { get; set; }
    }
}
