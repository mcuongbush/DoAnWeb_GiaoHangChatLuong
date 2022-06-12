namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("HangHoa")]
    public partial class HangHoa
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public HangHoa()
        {
            CTHDs = new HashSet<CTHD>();
        }

        [Key]
        [StringLength(10)]
        public string MaHH { get; set; }

        [Required]
        [StringLength(100)]
        public string TenHH { get; set; }

        [StringLength(100)]
        public string MoTa { get; set; }

        [Required]
        [StringLength(10)]
        public string DonVT { get; set; }

        [StringLength(10)]
        public string MaLHH { get; set; }

        public int GiaTien { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<CTHD> CTHDs { get; set; }

        public virtual LoaiHH LoaiHH { get; set; }
    }
}
