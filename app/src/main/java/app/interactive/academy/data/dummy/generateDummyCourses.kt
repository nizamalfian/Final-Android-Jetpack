package app.interactive.academy.data.dummy
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.remote.response.ContentResponse
import app.interactive.academy.data.source.remote.response.CourseResponse
import app.interactive.academy.data.source.remote.response.ModuleResponse


/**
 * Created by nizamalfian on 01/07/2019.
 */

fun generateRemoteCourses():ArrayList<CourseResponse>{
    return ArrayList<CourseResponse>().apply{
        add(
            CourseResponse(
                "a14",
                "Menjadi Android Developer Expert",
                "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
                "100 Hari",
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"
            )
        )
        add(
            CourseResponse(
                "a55",
                "Kotlin Android Developer Expert",
                "Pada Google I/O 2017, Kotlin diumumkan sebagai bahasa pemrograman yang termasuk dalam bahasa kelas satu (First class) yang didukung untuk pembuatan aplikasi Android, selain Java dan C++. Kotlin adalah bahasa pemrograman yang dibuat oleh JetBrains. Google juga akan memastikan bahwa semua fitur baru di Android, framework, IDE dan keseluruhan library, akan dapat bekerja dan terintegrasi baik dengan bahasa pemrograman Kotlin serta interopable dengan fungsi-fungsi Java yang telah ada sehingga memungkinkan para engineer melakukan perubahan bagian tertentu aplikasi dari Java ke Kotlin dan sebaliknya dengan sangat mudah.",
                "50 Hari",
                "https://www.dicoding.com/images/small/academy/kotlin_android_developer_expert_logo_070119140227.jpg"
            )
        )
        add(
            CourseResponse(
                "a47",
                "Menjadi Game Developer Expert",
                "Semua modul dalam kelas ini telah diverifikasi langsung oleh Asosiasi Game Indonesia (AGI) untuk memastikan materi yang diajarkan relevan dan sesuai dengan kebutuhan industri game saat ini. Peserta akan belajar best practice membuat game seperti Script, Sprite, UI, Gameplay, Input Method, Porting ke Android / iOS, Modul Services (Collaboration, Ads, Analytics dan Google Play Games - Update Oktober 2018), Porting ke VR - Cardboard dan Gear VR (New Mei 2018) dengan Unity 3D. Peserta juga akan belajar langsung membuat 8 game yaitu Casual (Update Agustus 2018), Pilah Sampah (New November 2018), Tower Defense (New November 2018), Arcade (Update Maret 2018), Platformer (Update Januari 2019), FPS, Multiplayer (Update Maret 2018), serta Game Interaktif dengan VR (New Mei 2018).",
                "75 Hari",
                "https://www.dicoding.com/images/small/academy/menjadi_game_developer_expert_logo_070119140532.jpg"
            )
        )
        add(
            CourseResponse(
                "a74",
                "Membangun Progressive Web Apps",
                "Progressive Web Apps adalah aplikasi web yang memanfaatkan beragam fitur web modern sehingga dapat menyajikan pengalaman pengguna seperti aplikasi native. PWA mengubah sajian tampilan yang umumnya dibuka melalui halaman browser menjadi jendela aplikasi tersendiri. Selain itu PWA juga memungkinkan konten halaman diakses dalam mode offline, menampilkan pesan pemberitahuan, hingga akses ke hardware dari perangkat seperti halnya native app.",
                "50 Hari",
                "https://www.dicoding.com/images/small/academy/membangun_progressive_web_apps_logo_070119142922.jpg"
            )
        )
        add(
            CourseResponse(
                "a51",
                "Belajar Membuat Aplikasi Android untuk Pemula",
                "Kelas ini didesain oleh Google Authorized Training Partner untuk developer Android di Indonesia. Peserta akan mempelajari materi dasar Android dalam 30 hari dan diarahkan untuk membuat aplikasi sederhana. Terdapat 27 modul yang juga merupakan bagian dari Kelas Menjadi Android Developer Expert. Peserta disarankan setidaknya memiliki pengetahuan tentang programming Java dan atau pemrograman berorientasi objek. Sistem pembelajaran adalah online (dapat diakses kapanpun dan darimanapun selama tersedia internet) dan kehadiran tatap muka tidak diperlukan. Tools yang diwajibkan untuk kelas belajar Android ini adalah Android Studio. Peserta harus submit satu proyek akhir yang akan direview oleh developer expert untuk mendapatkan sertifikat dari kelas ini. Bila menginginkan materi yang lebih komprehensif, silahkan mengikuti kelas Menjadi Android Developer Expert.",
                "30 Hari",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg"
            )
        )
    }
}

fun generateRemoteModules(courseId:String):ArrayList<ModuleResponse>{
    return ArrayList<ModuleResponse>().apply {
        add(
            ModuleResponse(
                String.format("%sm1", courseId),
                courseId,
                "Modul 0 : Introduction",
                0
            )
        )
        add(
            ModuleResponse(
                String.format("%sm2", courseId),
                courseId,
                "Modul 1 : Teori 1",
                1
            )
        )
        add(
            ModuleResponse(
                String.format("%sm3", courseId),
                courseId,
                "Latihan 1",
                2
            )
        )
        add(
            ModuleResponse(
                String.format("%sm4", courseId),
                courseId,
                "Bedah Kode 1",
                3
            )
        )
        add(
            ModuleResponse(
                String.format("%sm5", courseId),
                courseId,
                "Modul 2 : Teori 2",
                4
            )
        )
        add(
            ModuleResponse(
                String.format("%sm6", courseId),
                courseId,
                "Latihan 2",
                5
            )
        )
        add(
            ModuleResponse(
                String.format("%sm7", courseId),
                courseId,
                "Bedah Kode 2",
                6
            )
        )
    }
}

fun getRemoteContent(moduleId:String): ContentResponse=
    ContentResponse(moduleId,"This is a dummy content")

fun generateDummyCourses(): ArrayList<CourseEntity> {
    return ArrayList<CourseEntity>().apply {
        add(
            CourseEntity(
                "a14",
                "Menjadi Android Developer Expert",
                "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
                "100 Hari",
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"
            )
        )
        add(
            CourseEntity(
                "a55",
                "Kotlin Android Developer Expert",
                "Pada Google I/O 2017, Kotlin diumumkan sebagai bahasa pemrograman yang termasuk dalam bahasa kelas satu (First class) yang didukung untuk pembuatan aplikasi Android, selain Java dan C++. Kotlin adalah bahasa pemrograman yang dibuat oleh JetBrains. Google juga akan memastikan bahwa semua fitur baru di Android, framework, IDE dan keseluruhan library, akan dapat bekerja dan terintegrasi baik dengan bahasa pemrograman Kotlin serta interopable dengan fungsi-fungsi Java yang telah ada sehingga memungkinkan para engineer melakukan perubahan bagian tertentu aplikasi dari Java ke Kotlin dan sebaliknya dengan sangat mudah.",
                "50 Hari",
                "https://www.dicoding.com/images/small/academy/kotlin_android_developer_expert_logo_070119140227.jpg"
            )
        )
        add(
            CourseEntity(
                "a47",
                "Menjadi Game Developer Expert",
                "Semua modul dalam kelas ini telah diverifikasi langsung oleh Asosiasi Game Indonesia (AGI) untuk memastikan materi yang diajarkan relevan dan sesuai dengan kebutuhan industri game saat ini. Peserta akan belajar best practice membuat game seperti Script, Sprite, UI, Gameplay, Input Method, Porting ke Android / iOS, Modul Services (Collaboration, Ads, Analytics dan Google Play Games - Update Oktober 2018), Porting ke VR - Cardboard dan Gear VR (New Mei 2018) dengan Unity 3D. Peserta juga akan belajar langsung membuat 8 game yaitu Casual (Update Agustus 2018), Pilah Sampah (New November 2018), Tower Defense (New November 2018), Arcade (Update Maret 2018), Platformer (Update Januari 2019), FPS, Multiplayer (Update Maret 2018), serta Game Interaktif dengan VR (New Mei 2018).",
                "75 Hari",
                "https://www.dicoding.com/images/small/academy/menjadi_game_developer_expert_logo_070119140532.jpg"
            )
        )
        add(
            CourseEntity(
                "a74",
                "Membangun Progressive Web Apps",
                "Progressive Web Apps adalah aplikasi web yang memanfaatkan beragam fitur web modern sehingga dapat menyajikan pengalaman pengguna seperti aplikasi native. PWA mengubah sajian tampilan yang umumnya dibuka melalui halaman browser menjadi jendela aplikasi tersendiri. Selain itu PWA juga memungkinkan konten halaman diakses dalam mode offline, menampilkan pesan pemberitahuan, hingga akses ke hardware dari perangkat seperti halnya native app.",
                "50 Hari",
                "https://www.dicoding.com/images/small/academy/membangun_progressive_web_apps_logo_070119142922.jpg"
            )
        )
        add(
            CourseEntity(
                "a51",
                "Belajar Membuat Aplikasi Android untuk Pemula",
                "Kelas ini didesain oleh Google Authorized Training Partner untuk developer Android di Indonesia. Peserta akan mempelajari materi dasar Android dalam 30 hari dan diarahkan untuk membuat aplikasi sederhana. Terdapat 27 modul yang juga merupakan bagian dari Kelas Menjadi Android Developer Expert. Peserta disarankan setidaknya memiliki pengetahuan tentang programming Java dan atau pemrograman berorientasi objek. Sistem pembelajaran adalah online (dapat diakses kapanpun dan darimanapun selama tersedia internet) dan kehadiran tatap muka tidak diperlukan. Tools yang diwajibkan untuk kelas belajar Android ini adalah Android Studio. Peserta harus submit satu proyek akhir yang akan direview oleh developer expert untuk mendapatkan sertifikat dari kelas ini. Bila menginginkan materi yang lebih komprehensif, silahkan mengikuti kelas Menjadi Android Developer Expert.",
                "30 Hari",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg"
            )
        )
    }
}

fun generateDummyModules(courseId:String):ArrayList<ModuleEntity>{
    return ArrayList<ModuleEntity>().apply {
        add(
            ModuleEntity(
                String.format("%sm1", courseId),
                courseId,
                "Modul 0 : Introduction",
                0
            )
        )
        add(
            ModuleEntity(
                String.format("%sm2", courseId),
                courseId,
                "Modul 1 : Teori 1",
                1
            )
        )
        add(
            ModuleEntity(
                String.format("%sm3", courseId),
                courseId,
                "Latihan 1",
                2
            )
        )
        add(
            ModuleEntity(
                String.format("%sm4", courseId),
                courseId,
                "Bedah Kode 1",
                3
            )
        )
        add(
            ModuleEntity(
                String.format("%sm5", courseId),
                courseId,
                "Modul 2 : Teori 2",
                4
            )
        )
        add(
            ModuleEntity(
                String.format("%sm6", courseId),
                courseId,
                "Latihan 2",
                5
            )
        )
        add(
            ModuleEntity(
                String.format("%sm7", courseId),
                courseId,
                "Bedah Kode 2",
                6
            )
        )
    }
}

fun getCourse(courseId:String): CourseEntity?{
    for(entity in app.interactive.academy.utils.generateDummyCourses()){
        if(entity.courseId == courseId)
            return entity
    }
    return null
}