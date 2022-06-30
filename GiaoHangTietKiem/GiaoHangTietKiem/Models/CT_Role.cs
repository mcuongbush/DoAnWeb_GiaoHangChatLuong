namespace GiaoHangTietKiem.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class CT_Role
    {
        [Key]
        public int MaCTRole { get; set; }

        [Required]
        [StringLength(50)]
        public string TenTK { get; set; }

        public int IDRole { get; set; }

        public virtual Role Role { get; set; }

        public virtual TaiKhoan TaiKhoan { get; set; }
    }
}
