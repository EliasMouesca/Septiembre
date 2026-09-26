# Firmar la app con 1Password

La clave de firma no está en el repositorio. Se guarda en el vault `Code`, en el ítem `Septiembre Android Signing`, junto con estos campos:

- `keystore`: archivo adjunto del keystore.
- `keystore_password`: contraseña del keystore.
- `key_alias`: alias `septiembre`.
- `key_password`: contraseña de la clave.

## Primera configuración en una computadora

Instalá Android Studio/SDK, el 1Password CLI 2 y autenticá la cuenta:

```bash
op --version
op account list
```

Después de clonar el repositorio, compilá la versión firmada con:

```bash
./scripts/build-release-with-1password.sh
```

El script recupera el keystore a un directorio temporal, inyecta las credenciales como variables de Gradle y elimina el directorio al terminar. No crea archivos de secretos dentro del proyecto.

El APK queda en `app/build/outputs/apk/release/app-release.apk`. Para instalarlo manualmente:

```bash
adb install -r app/build/outputs/apk/release/app-release.apk
```

Si el teléfono tiene instalada la variante debug, hay que desinstalarla una vez antes de instalar la release porque tienen firmas distintas:

```bash
adb uninstall com.elicapo.yaesseptiembre
```

Conservá el ítem y sus contraseñas: cambiar la clave de firma impide actualizar la aplicación ya instalada.
