
    @echo off
               title TrisidiaX 12 hour Auto Restarter
        :start
               set time=43200
               start run.bat
        :loop
               cls
               IF %time% GTR 0 (
                set /a time=%time% - 1
                 set /a min=%time%/60
		set /a hour=%time%/24
                 echo   %time% Seconds is %min% Minutes.
                ping 127.0.0.1 -n 2 > NUL
               goto loop
               )
        taskkill /f /im java.exe
       cls
      goto start
  ::Made By TrisidiaX