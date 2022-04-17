/* See: https://github.com/tadfisher/android-nixpkgs */

{ pkgs ? import <nixpkgs> { }, system ? builtins.currentSystem }:


let
  unstable = import (fetchTarball https://nixos.org/channels/nixos-unstable/nixexprs.tar.xz) { };
  devshell = import (fetchTarball "https://github.com/numtide/devshell/archive/master.tar.gz") { inherit system; };

  android-nixpkgs = pkgs.callPackage (import (builtins.fetchGit {
    url = "https://github.com/tadfisher/android-nixpkgs.git";
  })) {
    # Default; can also choose "beta", "preview", or "canary".
    channel = "stable";
  };

  android-sdk = android-nixpkgs.sdk (sdkPkgs: with sdkPkgs; [
    cmdline-tools-latest
    build-tools-33-0-0-rc2
    patcher-v4
    platform-tools
    platforms-android-31
  ]);
in
# Configure your development environment.
#
# Documentation: https://github.com/numtide/devshell
devshell.mkShell {
  name = "android-project";
  motd = ''
    Entered the Android app development environment.
  '';
  env = [
    {
      name = "ANDROID_HOME";
      value = "${android-sdk}/share/android-sdk";
    }
    {
      name = "ANDROID_SDK_ROOT";
      value = "${android-sdk}/share/android-sdk";
    }
    {
      name = "JAVA_HOME";
      value = pkgs.jdk11.home;
    }
  ];
  packages = [
    pkgs.android-studio
    pkgs.gradle
    pkgs.jdk11
    android-sdk
  ];
}
