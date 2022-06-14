using System;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity;
using System.Linq;

namespace GiaoHangTietKiem
{
    public partial class GiaohangchatluongContext : DbContext
    {
        public GiaohangchatluongContext()
            : base("name=GiaohangchatluongContext")
        {
        }

        public virtual DbSet<CT_TuyenDuong> CT_TuyenDuong { get; set; }
        public virtual DbSet<CTHD> CTHDs { get; set; }
        public virtual DbSet<CTVanChuyen> CTVanChuyens { get; set; }
        public virtual DbSet<HangHoa> HangHoas { get; set; }
        public virtual DbSet<HoaDonVanChuyen> HoaDonVanChuyens { get; set; }
        public virtual DbSet<KhachHang> KhachHangs { get; set; }
        public virtual DbSet<KhachNhan> KhachNhans { get; set; }
        public virtual DbSet<KhuVuc> KhuVucs { get; set; }
        public virtual DbSet<LoaiHH> LoaiHHs { get; set; }
        public virtual DbSet<LoaiVanChuyen> LoaiVanChuyens { get; set; }
        public virtual DbSet<NhaKho> NhaKhoes { get; set; }
        public virtual DbSet<NhanVien> NhanViens { get; set; }
        public virtual DbSet<PhieuGuiHang> PhieuGuiHangs { get; set; }
        public virtual DbSet<PhongBan> PhongBans { get; set; }
        public virtual DbSet<Shop> Shops { get; set; }
        public virtual DbSet<sysdiagram> sysdiagrams { get; set; }
        public virtual DbSet<TaiKhoan> TaiKhoans { get; set; }
        public virtual DbSet<TuyenDuong> TuyenDuongs { get; set; }
        public virtual DbSet<UserKH> UserKHs { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CT_TuyenDuong>()
                .Property(e => e.Ma_CTTD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CT_TuyenDuong>()
                .Property(e => e.MaNK)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CT_TuyenDuong>()
                .Property(e => e.MaTD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CT_TuyenDuong>()
                .Property(e => e.MaKhoDen)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CTHD>()
                .Property(e => e.MaCTHD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CTHD>()
                .Property(e => e.SoHD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CTHD>()
                .Property(e => e.MaHH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CTVanChuyen>()
                .Property(e => e.MaCTVC)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CTVanChuyen>()
                .Property(e => e.SoHD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<CTVanChuyen>()
                .Property(e => e.MaNK)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<HangHoa>()
                .Property(e => e.MaHH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<HangHoa>()
                .Property(e => e.MaLHH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<HoaDonVanChuyen>()
                .Property(e => e.SoHD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<HoaDonVanChuyen>()
                .Property(e => e.SoPGH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<HoaDonVanChuyen>()
                .Property(e => e.MaNV)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<HoaDonVanChuyen>()
                .Property(e => e.MaTD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<KhachHang>()
                .Property(e => e.MaKH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<KhachHang>()
                .Property(e => e.SDT)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<KhachNhan>()
                .Property(e => e.MaKN)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<KhachNhan>()
                .Property(e => e.SDT)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<KhuVuc>()
                .Property(e => e.MaKV)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<LoaiHH>()
                .Property(e => e.MaLHH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<LoaiVanChuyen>()
                .Property(e => e.MaLVC)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<NhaKho>()
                .Property(e => e.MaNK)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<NhaKho>()
                .Property(e => e.MaKV)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<NhaKho>()
                .HasMany(e => e.CT_TuyenDuong)
                .WithRequired(e => e.NhaKho)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<NhanVien>()
                .Property(e => e.MaNV)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<NhanVien>()
                .Property(e => e.MaPB)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<NhanVien>()
                .Property(e => e.SDT)
                .IsFixedLength();

            modelBuilder.Entity<NhanVien>()
                .Property(e => e.MaNK)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<NhanVien>()
                .HasMany(e => e.TaiKhoans)
                .WithRequired(e => e.NhanVien)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<PhieuGuiHang>()
                .Property(e => e.SoPGH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<PhieuGuiHang>()
                .Property(e => e.MaKH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<PhieuGuiHang>()
                .Property(e => e.MaLVC)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<PhieuGuiHang>()
                .Property(e => e.MaNV)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<PhieuGuiHang>()
                .Property(e => e.MaKN)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<PhongBan>()
                .Property(e => e.MaPB)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<PhongBan>()
                .HasMany(e => e.NhanViens)
                .WithRequired(e => e.PhongBan)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Shop>()
                .Property(e => e.MaShop)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<Shop>()
                .Property(e => e.TkNganHang)
                .IsUnicode(false);

            modelBuilder.Entity<Shop>()
                .Property(e => e.MaKH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<TaiKhoan>()
                .Property(e => e.TenTK)
                .IsUnicode(false);

            modelBuilder.Entity<TaiKhoan>()
                .Property(e => e.MatKhau)
                .IsUnicode(false);

            modelBuilder.Entity<TaiKhoan>()
                .Property(e => e.Email)
                .IsUnicode(false);

            modelBuilder.Entity<TaiKhoan>()
                .Property(e => e.MaNV)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<TuyenDuong>()
                .Property(e => e.MaTD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<TuyenDuong>()
                .Property(e => e.MaKhoBD)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<TuyenDuong>()
                .Property(e => e.MaKhoKT)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<TuyenDuong>()
                .Property(e => e.MaLVC)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<TuyenDuong>()
                .HasMany(e => e.CT_TuyenDuong)
                .WithRequired(e => e.TuyenDuong)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<UserKH>()
                .Property(e => e.SDT)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<UserKH>()
                .Property(e => e.Email)
                .IsUnicode(false);

            modelBuilder.Entity<UserKH>()
                .Property(e => e.MaKH)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<UserKH>()
                .Property(e => e.UserName)
                .IsUnicode(false);
        }
    }
}
