rootProject.name = "day2"
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml")) // ✅ TOML 파일 경로 명시
        }
    }
}