namespace GiaoHangTietKiem.Migrations
{
    using System;
    using System.Data.Entity.Migrations;

    public partial class AddProduct : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.CT_TuyenDuong",
                c => new
                {
                    Ma_CTTD = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    MaNK = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    MaTD = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    MaKhoDen = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.Ma_CTTD)
                .ForeignKey("dbo.NhaKho", t => t.MaNK)
                .ForeignKey("dbo.TuyenDuong", t => t.MaTD)
                .Index(t => t.MaNK)
                .Index(t => t.MaTD);

            CreateTable(
                "dbo.NhaKho",
                c => new
                {
                    MaNK = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenNK = c.String(nullable: false, maxLength: 100),
                    DienTich = c.Double(),
                    SucChua = c.Int(),
                    DiaChi = c.String(maxLength: 100),
                    MaKV = c.String(maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.MaNK)
                .ForeignKey("dbo.KhuVuc", t => t.MaKV)
                .Index(t => t.MaKV);

            CreateTable(
                "dbo.CTVanChuyen",
                c => new
                {
                    MaCTVC = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    SoHD = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    MaNK = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    NgayNhapKho = c.DateTime(),
                    NgayXuatKho = c.DateTime(),
                })
                .PrimaryKey(t => t.MaCTVC)
                .ForeignKey("dbo.HoaDonVanChuyen", t => t.SoHD)
                .ForeignKey("dbo.NhaKho", t => t.MaNK)
                .Index(t => t.SoHD)
                .Index(t => t.MaNK);

            CreateTable(
                "dbo.HoaDonVanChuyen",
                c => new
                {
                    SoHD = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    NgayLapHD = c.DateTime(storeType: "date"),
                    TongTien = c.Long(),
                    SoPGH = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    MaNV = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    TrangThai = c.Boolean(nullable: false),
                    MaTD = c.String(maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.SoHD)
                .ForeignKey("dbo.NhanVien", t => t.MaNV)
                .ForeignKey("dbo.PhieuGuiHang", t => t.SoPGH)
                .ForeignKey("dbo.TuyenDuong", t => t.MaTD)
                .Index(t => t.SoPGH)
                .Index(t => t.MaNV)
                .Index(t => t.MaTD);

            CreateTable(
                "dbo.CTHD",
                c => new
                {
                    MaCTHD = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    SoHD = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    MaHH = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    SoLuong = c.Int(nullable: false),
                    KG = c.Double(nullable: false),
                })
                .PrimaryKey(t => t.MaCTHD)
                .ForeignKey("dbo.HangHoa", t => t.MaHH)
                .ForeignKey("dbo.HoaDonVanChuyen", t => t.SoHD)
                .Index(t => t.SoHD)
                .Index(t => t.MaHH);

            CreateTable(
                "dbo.HangHoa",
                c => new
                {
                    MaHH = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenHH = c.String(nullable: false, maxLength: 100),
                    MoTa = c.String(maxLength: 100),
                    DonVT = c.String(nullable: false, maxLength: 10),
                    MaLHH = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    GiaTien = c.Int(nullable: false),
                })
                .PrimaryKey(t => t.MaHH)
                .ForeignKey("dbo.LoaiHH", t => t.MaLHH)
                .Index(t => t.MaLHH);

            CreateTable(
                "dbo.LoaiHH",
                c => new
                {
                    MaLHH = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenLHH = c.String(nullable: false, maxLength: 100),
                })
                .PrimaryKey(t => t.MaLHH);

            CreateTable(
                "dbo.NhanVien",
                c => new
                {
                    MaNV = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenNV = c.String(nullable: false, maxLength: 50),
                    NgaySinh = c.DateTime(nullable: false, storeType: "date"),
                    DiaChi = c.String(nullable: false, maxLength: 100),
                    ChucVu = c.String(nullable: false, maxLength: 100),
                    BacLuong = c.Double(nullable: false),
                    MaPB = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    SDT = c.String(maxLength: 10, fixedLength: true),
                    GioiTinh = c.Boolean(),
                    MaNK = c.String(maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.MaNV)
                .ForeignKey("dbo.NhaKho", t => t.MaNK)
                .ForeignKey("dbo.PhongBan", t => t.MaPB)
                .Index(t => t.MaPB)
                .Index(t => t.MaNK);

            CreateTable(
                "dbo.PhieuGuiHang",
                c => new
                {
                    SoPGH = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    NgayGui = c.DateTime(storeType: "date"),
                    COD = c.Boolean(),
                    MaKH = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    MaLVC = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    MaNV = c.String(maxLength: 10, fixedLength: true, unicode: false),
                    MaKN = c.String(maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.SoPGH)
                .ForeignKey("dbo.KhachHang", t => t.MaKH)
                .ForeignKey("dbo.KhachNhan", t => t.MaKN)
                .ForeignKey("dbo.LoaiVanChuyen", t => t.MaLVC)
                .ForeignKey("dbo.NhanVien", t => t.MaNV)
                .Index(t => t.MaKH)
                .Index(t => t.MaLVC)
                .Index(t => t.MaNV)
                .Index(t => t.MaKN);

            CreateTable(
                "dbo.KhachHang",
                c => new
                {
                    MaKH = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenKH = c.String(nullable: false, maxLength: 50),
                    SDT = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    DiaChi = c.String(maxLength: 100),
                    GioiTinh = c.Boolean(nullable: false),
                })
                .PrimaryKey(t => t.MaKH);

            CreateTable(
                "dbo.Shop",
                c => new
                {
                    MaShop = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenShop = c.String(nullable: false, maxLength: 50),
                    NgayDK = c.DateTime(nullable: false),
                    TkNganHang = c.String(nullable: false, maxLength: 15, unicode: false),
                    MaKH = c.String(maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.MaShop)
                .ForeignKey("dbo.KhachHang", t => t.MaKH)
                .Index(t => t.MaKH);

            CreateTable(
                "dbo.UserKH",
                c => new
                {
                    SDT = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    MatKhau = c.String(nullable: false, maxLength: 20),
                    Email = c.String(nullable: false, maxLength: 20, unicode: false),
                    MaKH = c.String(maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.SDT)
                .ForeignKey("dbo.KhachHang", t => t.MaKH)
                .Index(t => t.MaKH);

            CreateTable(
                "dbo.KhachNhan",
                c => new
                {
                    MaKN = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenKN = c.String(nullable: false, maxLength: 50),
                    SDT = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    DiaChi = c.String(maxLength: 100),
                    GioiTinh = c.Boolean(nullable: false),
                })
                .PrimaryKey(t => t.MaKN);

            CreateTable(
                "dbo.LoaiVanChuyen",
                c => new
                {
                    MaLVC = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenLVC = c.String(nullable: false, maxLength: 100),
                    Gia = c.Double(nullable: false),
                })
                .PrimaryKey(t => t.MaLVC);

            CreateTable(
                "dbo.Thue",
                c => new
                {
                    MaThue = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    GiaTri = c.Long(nullable: false),
                    KhoangCach = c.Int(nullable: false),
                    MaLVC = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.MaThue)
                .ForeignKey("dbo.LoaiVanChuyen", t => t.MaLVC)
                .Index(t => t.MaLVC);

            CreateTable(
                "dbo.TuyenDuong",
                c => new
                {
                    MaTD = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenTD = c.String(nullable: false, maxLength: 50),
                    ThoiGian = c.Int(nullable: false),
                    MaKhoBD = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    MaKhoKT = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    MaLVC = c.String(maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.MaTD)
                .ForeignKey("dbo.LoaiVanChuyen", t => t.MaLVC)
                .Index(t => t.MaLVC);

            CreateTable(
                "dbo.PhongBan",
                c => new
                {
                    MaPB = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenPB = c.String(nullable: false, maxLength: 50),
                    NhiemVu = c.String(maxLength: 100),
                })
                .PrimaryKey(t => t.MaPB);

            CreateTable(
                "dbo.TaiKhoan",
                c => new
                {
                    TenTK = c.String(nullable: false, maxLength: 50, fixedLength: true, unicode: false),
                    MatKhau = c.String(nullable: false, maxLength: 50, fixedLength: true, unicode: false),
                    Email = c.String(nullable: false, maxLength: 50, fixedLength: true, unicode: false),
                    LoaiTK = c.Boolean(),
                    MaNV = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                })
                .PrimaryKey(t => t.TenTK)
                .ForeignKey("dbo.NhanVien", t => t.MaNV)
                .Index(t => t.MaNV);

            CreateTable(
                "dbo.KhuVuc",
                c => new
                {
                    MaKV = c.String(nullable: false, maxLength: 10, fixedLength: true, unicode: false),
                    TenKV = c.String(nullable: false, maxLength: 100),
                    SoNhaKho = c.Int(nullable: false),
                })
                .PrimaryKey(t => t.MaKV);

            CreateTable(
                "dbo.sysdiagrams",
                c => new
                {
                    diagram_id = c.Int(nullable: false, identity: true),
                    name = c.String(nullable: false, maxLength: 128),
                    principal_id = c.Int(nullable: false),
                    version = c.Int(),
                    definition = c.Binary(),
                })
                .PrimaryKey(t => t.diagram_id);

        }

        public override void Down()
        {
            DropForeignKey("dbo.NhaKho", "MaKV", "dbo.KhuVuc");
            DropForeignKey("dbo.CTVanChuyen", "MaNK", "dbo.NhaKho");
            DropForeignKey("dbo.TaiKhoan", "MaNV", "dbo.NhanVien");
            DropForeignKey("dbo.NhanVien", "MaPB", "dbo.PhongBan");
            DropForeignKey("dbo.PhieuGuiHang", "MaNV", "dbo.NhanVien");
            DropForeignKey("dbo.TuyenDuong", "MaLVC", "dbo.LoaiVanChuyen");
            DropForeignKey("dbo.HoaDonVanChuyen", "MaTD", "dbo.TuyenDuong");
            DropForeignKey("dbo.CT_TuyenDuong", "MaTD", "dbo.TuyenDuong");
            DropForeignKey("dbo.Thue", "MaLVC", "dbo.LoaiVanChuyen");
            DropForeignKey("dbo.PhieuGuiHang", "MaLVC", "dbo.LoaiVanChuyen");
            DropForeignKey("dbo.PhieuGuiHang", "MaKN", "dbo.KhachNhan");
            DropForeignKey("dbo.UserKH", "MaKH", "dbo.KhachHang");
            DropForeignKey("dbo.Shop", "MaKH", "dbo.KhachHang");
            DropForeignKey("dbo.PhieuGuiHang", "MaKH", "dbo.KhachHang");
            DropForeignKey("dbo.HoaDonVanChuyen", "SoPGH", "dbo.PhieuGuiHang");
            DropForeignKey("dbo.NhanVien", "MaNK", "dbo.NhaKho");
            DropForeignKey("dbo.HoaDonVanChuyen", "MaNV", "dbo.NhanVien");
            DropForeignKey("dbo.CTVanChuyen", "SoHD", "dbo.HoaDonVanChuyen");
            DropForeignKey("dbo.CTHD", "SoHD", "dbo.HoaDonVanChuyen");
            DropForeignKey("dbo.HangHoa", "MaLHH", "dbo.LoaiHH");
            DropForeignKey("dbo.CTHD", "MaHH", "dbo.HangHoa");
            DropForeignKey("dbo.CT_TuyenDuong", "MaNK", "dbo.NhaKho");
            DropIndex("dbo.TaiKhoan", new[] { "MaNV" });
            DropIndex("dbo.TuyenDuong", new[] { "MaLVC" });
            DropIndex("dbo.Thue", new[] { "MaLVC" });
            DropIndex("dbo.UserKH", new[] { "MaKH" });
            DropIndex("dbo.Shop", new[] { "MaKH" });
            DropIndex("dbo.PhieuGuiHang", new[] { "MaKN" });
            DropIndex("dbo.PhieuGuiHang", new[] { "MaNV" });
            DropIndex("dbo.PhieuGuiHang", new[] { "MaLVC" });
            DropIndex("dbo.PhieuGuiHang", new[] { "MaKH" });
            DropIndex("dbo.NhanVien", new[] { "MaNK" });
            DropIndex("dbo.NhanVien", new[] { "MaPB" });
            DropIndex("dbo.HangHoa", new[] { "MaLHH" });
            DropIndex("dbo.CTHD", new[] { "MaHH" });
            DropIndex("dbo.CTHD", new[] { "SoHD" });
            DropIndex("dbo.HoaDonVanChuyen", new[] { "MaTD" });
            DropIndex("dbo.HoaDonVanChuyen", new[] { "MaNV" });
            DropIndex("dbo.HoaDonVanChuyen", new[] { "SoPGH" });
            DropIndex("dbo.CTVanChuyen", new[] { "MaNK" });
            DropIndex("dbo.CTVanChuyen", new[] { "SoHD" });
            DropIndex("dbo.NhaKho", new[] { "MaKV" });
            DropIndex("dbo.CT_TuyenDuong", new[] { "MaTD" });
            DropIndex("dbo.CT_TuyenDuong", new[] { "MaNK" });
            DropTable("dbo.sysdiagrams");
            DropTable("dbo.KhuVuc");
            DropTable("dbo.TaiKhoan");
            DropTable("dbo.PhongBan");
            DropTable("dbo.TuyenDuong");
            DropTable("dbo.Thue");
            DropTable("dbo.LoaiVanChuyen");
            DropTable("dbo.KhachNhan");
            DropTable("dbo.UserKH");
            DropTable("dbo.Shop");
            DropTable("dbo.KhachHang");
            DropTable("dbo.PhieuGuiHang");
            DropTable("dbo.NhanVien");
            DropTable("dbo.LoaiHH");
            DropTable("dbo.HangHoa");
            DropTable("dbo.CTHD");
            DropTable("dbo.HoaDonVanChuyen");
            DropTable("dbo.CTVanChuyen");
            DropTable("dbo.NhaKho");
            DropTable("dbo.CT_TuyenDuong");
        }
    }
}
